package loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class Loader {

	private String configFilename = "config";
	private String pluginPathDirectory;

	private Properties configPlatformData;
	private static Map<String, Properties> configPluginsData = new HashMap<String, Properties>();
	private static Map<String, Object> plugin = new HashMap<String, Object>();
	private static URLClassLoader platformClassLoader;

	public static void main(String[] args) throws FileNotFoundException {

		new Loader();

	}
	
	public static void log(String msg){
		System.out.println("LOG : "+msg);
	}

	public Loader() {
		super();
		// TODO Auto-generated constructor stub
		try {
			init();
			loadDefaultPlugins();
		} catch (IOException | ClassNotFoundException | NoSuchMethodException
				| InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Récupérer info sur la plateforme depuis son fichier de config
	private void init() throws IOException, FileNotFoundException {
		System.out.println("[DEBUT]Phase d'initialisation de la plateforme.");

		this.setConfigPlatformData(new Properties());

		this.configPlatformData.load(new FileInputStream(configFilename));
		System.out.println("->Fichier de config de la plateforme chargé.");

		this.setPluginPathDirectory(System.getProperty("user.home")
				+ File.separator
				+ configPlatformData.getProperty("pluginPathDirFromHome"));
		System.out.println("->Répertoire où se situeront les plugins: "
				+ pluginPathDirectory);

		loadConfigPluginsData();
		System.out.println("[FIN]Phase d'initialisation de la plateforme.");

	}

	private List<URL> createUrlFromUrlFile() throws FileNotFoundException,
			IOException, MalformedURLException {
		// /Refactor: extract Method
		InputStream ips = new FileInputStream("pseudoClasspath/url");
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String ligne;
		URL currentUrl;
		List<URL> url = new ArrayList<URL>();
		while ((ligne = br.readLine()) != null) {
			System.out.println("Ligne lue: " + ligne);
			currentUrl = new URL(ligne);
			url.add(currentUrl);
		}
		br.close();
		return url;
	}

	public void createPlatformClassLoader(List<String> listPluginDirName)
			throws MalformedURLException {

		String firstPartOfUrlString = "file:";
		String lastPartOfUrlString = "bin/";
		String urlString;
		URL currentUrl;
		List<URL> url = new ArrayList<URL>();

		for (String pluginDirName : listPluginDirName) {

			urlString = firstPartOfUrlString + pluginPathDirectory
					+ File.separator + pluginDirName + File.separator
					+ lastPartOfUrlString;
			System.out.println("->Url trouvé par la plateforme: " + urlString);
			currentUrl = new URL(urlString);
			url.add(currentUrl);

		}

		this.setPlatformClassLoader(new URLClassLoader(url.toArray(new URL[url
				.size()])));
		System.out.println("->ClassLoader de la platforme créée.");
	}

	private static List<Object> getDependancesAndTheirInterf(Properties prop,
			String type) {
		System.out
				.println("-->[DEBUT]Recherche des dépendances obligatoires du plugin: "
						+ prop.get("name"));

		Boolean dependanceIsAPlugin = true;
		Object plugin = null;

		// Each String contained will permit to obtain the Class object
		// representing each interface implement by each dependance of concerned
		// plugin
		List<String> dependancesInterf = new ArrayList<String>();

		List<Object> currentPluginDependanceWithTeirInterf = new ArrayList<Object>();
		List<Object> dependances = new ArrayList<Object>();
		String toCompare = "producer";

		// To load a plugin with his dependances we need for each one her
		// interface
		List<Object> dependancesWithTheirInterf = new ArrayList<Object>();

		// Permit to check dependancies of current dependance found.(recursive
		// call)
		Properties propOfCurrentDepPlugin = null;
		// Do a checking on interface implementation of plugin next.
		String interf = "";

		String dependanceString = prop.getProperty("dependances" + type);

		if (!dependanceString.isEmpty()) {

			for (String dep : dependanceString.split(",")) {
				// This sequence permit to know the interface of the current
				// dependances
				System.out.println("-->Dépendance trouvée: " + dep
						+ " pour le plugin" + prop.get("name"));
				if (dep.contains(".I")) {
					interf = dep;
					dependancesInterf.add(interf);

				} else {

					// In this case dep contains the name of the current
					// dependance,
					// try to get its config(a Properties).
					// If it doesn't present, the dependance is not a plugin but
					// product by a plugin (Example a data)

					if (configPluginsData.containsKey(dep)) {

						propOfCurrentDepPlugin = configPluginsData.get(dep);
					} else {
						dependanceIsAPlugin = false;

						// ///////////////////////Recherche du plugin produisant
						// la dependance courante////////////

						for (Properties CurrentProp : configPluginsData
								.values()) {

							if ((CurrentProp.getProperty("type")
									.equals(toCompare))
									&& (CurrentProp.getProperty("product")
											.equals(interf))) {
								propOfCurrentDepPlugin = CurrentProp;
								break;
							}
						}
					}

					currentPluginDependanceWithTeirInterf = getDependancesAndTheirInterf(
							propOfCurrentDepPlugin, "Obligatoires");

					if (currentPluginDependanceWithTeirInterf != null) {

						try {
							plugin = instanceCreatorWithConstructor(
									currentPluginDependanceWithTeirInterf,
									propOfCurrentDepPlugin);

						} catch (ClassNotFoundException | NoSuchMethodException
								| InstantiationException
								| IllegalAccessException
								| InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {

						try {
							plugin = instanceCreator(propOfCurrentDepPlugin);

						} catch (ClassNotFoundException
								| InstantiationException
								| IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (dependanceIsAPlugin) {

						dependances.add(plugin);

					} else {

						Object ob = null;
						try {
							ob = (plugin.getClass()
									.getMethod("get" + dep, null)).invoke(
									plugin, null);

						} catch (IllegalAccessException
								| IllegalArgumentException
								| InvocationTargetException
								| NoSuchMethodException | SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dependances.add(ob);

					}
					System.out.println("-->" + dep + " chargé.");
				}
			}
			dependancesWithTheirInterf.add(dependances);
			dependancesWithTheirInterf.add(dependancesInterf);
			System.out.println("-->[FIN]Recherche des dépendances obligatoires du plugin: "+ prop.get("name"));
			return dependancesWithTheirInterf;

		}
		System.out
				.println("-->[FIN]Recherche des dépendances obligatoires du plugin: "
						+ prop.get("name"));
		return null;

	}

	private static Object instanceCreatorWithConstructor(
			List<Object> currentPluginDependance,
			Properties propOfCurrentDepPlugin) throws ClassNotFoundException,
			NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException {

		Class<?> currentPluginClass = Class.forName(
				propOfCurrentDepPlugin.getProperty("packageLocation"), false,
				platformClassLoader);
		Constructor construt = currentPluginClass
				.getConstructor(getClassFromString((List<Object>) currentPluginDependance
						.get(1)));
		Object ob = construt
				.newInstance(listToArray((List<Object>) currentPluginDependance
						.get(0)));

		return ob;
	}

	private static Object[] listToArray(List<Object> currentPluginDependance) {
		return currentPluginDependance
				.toArray(new Object[currentPluginDependance.size()]);
	}

	public Properties getConfigPlatformData() {
		return configPlatformData;
	}

	public void setConfigPlatformData(Properties configPlatformData) {
		this.configPlatformData = configPlatformData;
	}

	/*
	 * public List<Object> loadAvailablePlugin() throws ClassNotFoundException,
	 * NoSuchMethodException, InstantiationException, IllegalAccessException,
	 * InvocationTargetException{
	 * 
	 * FileInputStream currentPluginFileTemp; Object pluginInstance;
	 * List<Object> availablePlugin = new ArrayList<Object>();
	 * 
	 * for(Properties propp : this.configPluginsData.values()){
	 * 
	 * pluginInstance = instanceCreatorWithDeps(propp);
	 * availablePlugin.add(pluginInstance);
	 * 
	 * this.plugin.put(propp.getProperty("name"),pluginInstance);
	 * 
	 * }
	 * 
	 * return availablePlugin; }
	 */

	private void loadConfigPluginsData() throws MalformedURLException {
		Properties prop;
		FileInputStream currentPluginFileTemp;
		List<String> allPluginDirName = new ArrayList<String>();
		File pluginPathFile = new File(this.pluginPathDirectory);

		if (pluginPathFile.isDirectory()) {

			for (String pluginDirName : pluginPathFile.list()) {
				System.out.println("dir:" + pluginDirName);
				prop = new Properties();
				try {
					currentPluginFileTemp = new FileInputStream(
							this.pluginPathDirectory + File.separator
									+ pluginDirName + File.separator
									+ configFilename);
					allPluginDirName.add(pluginDirName);

					/* creation de l'instance d'une classe du plugin */
					prop.load(currentPluginFileTemp);

					this.configPluginsData.put(prop.getProperty("name"), prop);

				} catch (FileNotFoundException e) {
					// A venir : log
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out
					.println("->Chargement du fichier de config pour tous les plugins disponibles.");
			createPlatformClassLoader(allPluginDirName);
		}
	}

	public void loadDefaultPlugins() throws IOException, FileNotFoundException,
			ClassNotFoundException, NoSuchMethodException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		System.out.println("\n[DEBUT]Chargement des plugins par défaut");

		Properties prop;

		Object personInstance = null;

		for (Properties propPlugin : this.configPluginsData.values()) {

			if (propPlugin.getProperty("default").equals("true")) {
				personInstance = instanceCreatorWithDeps(propPlugin);
				System.out.println("->Plugin par défaut chargé: "+ propPlugin.get("name"));
				this.plugin.put(propPlugin.getProperty("name"), personInstance);
			}

		}

		System.out.println("[FIN]Chargement des plugins par défaut");

	}

	private static Object instanceCreatorWithDeps(Properties prop)
			throws ClassNotFoundException, NoSuchMethodException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		List<Object> dependances;
		Object objcetInstance;

		dependances = getDependancesAndTheirInterf(prop, "Obligatoires");

		if (dependances != null) {

			System.out.println("--->Appel du constructeur spécifique de: "+ prop.get("name"));
			System.out.println("--->Dépendances obligatoires trouvées");
			objcetInstance = instanceCreatorWithConstructor(dependances, prop);

		} else {
			System.out.println("--->Appel du constructeur par défaut de: "
					+ prop.get("name"));
			System.out
					.println("--->Aucune dépendances obligatoires trouvées");
			objcetInstance = instanceCreator(prop);
		}

		return objcetInstance;
	}

	private static Object instanceCreator(Properties prop)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		Object objectInstance;
		Class<?> objectClass;
		objectClass = Class.forName(prop.getProperty("packageLocation"), false, platformClassLoader);
		objectInstance = objectClass.newInstance();
		prop.setProperty("load", "true");
		return objectInstance;
	}

	private static Class<?>[] getClassFromString(List<Object> listOfStringClass)
			throws ClassNotFoundException {

		Class<?> classOfEachObjects[] = new Class<?>[listOfStringClass.size()];
		int p = 0;
		for (Object ob : listOfStringClass) {
			System.out.println("GetClassFromString: " + ob);
			classOfEachObjects[p] = Class.forName((String) ob, false,
					platformClassLoader);
			p++;
		}

		return classOfEachObjects;

	}

	// Renvoie une instance identifiée par son nom et son interface principale
	// Si plusieurs type de interf
	public static Object getInstanceOf(String interf, String nameInstance)
			throws ClassNotFoundException, NoSuchMethodException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		Boolean isPlugin = true;
		String pluginType;
		String pluginTarget;
		Properties propOfCurrentDepPlugin = null;
		Object tempInstance = plugin.get(nameInstance);

		if (tempInstance != null) {

			return tempInstance;

		} else {

			if (configPluginsData.containsKey(nameInstance)) {

				propOfCurrentDepPlugin = configPluginsData.get(nameInstance);

			} else {
				isPlugin = false;
				for (Properties CurrentProp : configPluginsData.values()) {

					if ((CurrentProp.getProperty("type").equals("producer"))
							&& (CurrentProp.getProperty("target")
									.equals(interf))) {
						propOfCurrentDepPlugin = CurrentProp;
						break;
					}
				}
			}

			tempInstance = instanceCreatorWithDeps(propOfCurrentDepPlugin);

			if (isPlugin) {

				return tempInstance;

			} else {

				Object ob = null;
				try {
					System.out.println("nameInstance --22 "+nameInstance);
					Method m = tempInstance.getClass().getMethod("get" + nameInstance, new Class<?>[] {});
					ob = m.invoke(tempInstance, new Object[] {});
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return ob;

			}

		}

	}

	public Properties getConfigPluginData(String pluginName,
			String interfOfDependance) {

		String toCompare = "producer";

		if (this.configPluginsData.containsKey(pluginName)) {

			return configPluginsData.get(pluginName);

		} else {

			for (Properties prop : this.configPluginsData.values()) {

				if ((prop.getProperty("type").equals(toCompare))
						&& (prop.getProperty("target")
								.equals(interfOfDependance))) {
					return prop;
				}

			}
		}

		System.out
				.println("Please verify : the dependance name or the interf of this one");
		return null;

	}

	public Map<String, Object> getPlugin() {
		return plugin;
	}

	public void setPlugin(Map<String, Object> plugin) {
		this.plugin = plugin;
	}

	public String getConfigFilename() {
		return configFilename;
	}

	public void setConfigFilename(String configFilename) {
		this.configFilename = configFilename;
	}

	public String getPluginPathDirectory() {
		return pluginPathDirectory;
	}

	public void setPluginPathDirectory(String pluginPathDirectory) {
		this.pluginPathDirectory = pluginPathDirectory;
	}

	public URLClassLoader getPlatformClassLoader() {
		return platformClassLoader;
	}

	public void setPlatformClassLoader(URLClassLoader platformClassLoader) {
		this.platformClassLoader = platformClassLoader;
	}
}
