package com.graphql.local.resolvers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class BaseResolver<T> implements GraphQLResolver<T> {
	
	@Autowired
	private LinkResolver linkResolver;
	@Autowired
	private Query queryResolver;
	@Autowired
	private Mutation mutationResolver;
	@Autowired
	private SigninResolver signinResolver;
	@Autowired
	private VoteResolver voteResolver;
//	  MongoDatabase mongo = new MongoClient().getDatabase("hackernews");
//      linkRepository = new LinkRepository(mongo.getCollection("links"));
//      userRepository = new UserRepository(mongo.getCollection("users"));
//      voteRepository = new VoteRepository(mongo.getCollection("votes"));
      
	@SuppressWarnings("rawtypes")
	public List<GraphQLResolver> getResolvers() {
		
		List<GraphQLResolver> resolvers = new ArrayList<>();
		resolvers.add(linkResolver);
		resolvers.add(queryResolver);
		resolvers.add(mutationResolver);
		resolvers.add(signinResolver);
		resolvers.add(voteResolver);
			/*
			 * List<Class> classes = getClasses(this.getClass().getPackage().getName());
			 * classes.forEach(cl -> { if(cl.isAnnotationPresent(GraphResolver.class)) {
			 * System.out.println("Classe: " + cl.getName()); try { String className =
			 * (String)PropertiesUtils.obterPropriedades(FILE_NAME_PROPERTIES,
			 * cl.getName()); Constructor constructor =
			 * cl.getConstructor(MongoCollection.class);
			 * resolvers.add((T)constructor.newInstance(mongo.getCollection(className))); }
			 * catch (Exception e) { log.error("Error: ", e); } } });
			 */
		return resolvers;
	}
	
//	 /**
//     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
//     *
//     * @param packageName The base package
//     * @return The classes
//     * @throws ClassNotFoundException
//     * @throws IOException
//     */
//    @SuppressWarnings("rawtypes")
//	private static List<Class> getClasses(String packageName)
//            throws ClassNotFoundException, IOException {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        assert classLoader != null;
//        String path = packageName.replace('.', '/');
//        Enumeration<URL> resources = classLoader.getResources(path);
//        List<File> dirs = new ArrayList<File>();
//        while (resources.hasMoreElements()) {
//            URL resource = (URL) resources.nextElement();
//            dirs.add(new File(resource.getFile()));
//        }
//        List<Class> classes = new ArrayList<Class>();
//        for (File directory : dirs) {
//            classes.addAll(findClasses(directory, packageName));
//        }
//        return classes;
//    }
//    /**
//     * Recursive method used to find all classes in a given directory and subdirs.
//     *
//     * @param directory   The base directory
//     * @param packageName The package name for classes found inside the base directory
//     * @return The classes
//     * @throws ClassNotFoundException
//     */
//    @SuppressWarnings("rawtypes")
//	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
//        List<Class> classes = new ArrayList<>();
//        if (!directory.exists()) {
//            return classes;
//        }
//        File[] files = directory.listFiles();
//        for (File file : files) {
//            if (file.isDirectory()) {
//                assert !file.getName().contains(".");
//                classes.addAll(findClasses(file, packageName + "." + file.getName()));
//            } else if (file.getName().endsWith(".class")) {
//                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
//            }
//        }
//        return classes;
//    }
}

