public class AllSamples {

    public static void main(String[] args) throws Exception {
        System.out.println("=============JSON============");
        //implementation("com.google.code.gson:gson:2.8.9")
        JsonConverter.main(args);
        System.out.println();

        System.out.println("=======StringsStorage========");
        StringsStorage.main(args);
        System.out.println();


        System.out.println("=====SerializableStorage=====");
        SerializableStorage.main(args);
        System.out.println();

        System.out.println("=========RawDatabase=========");
        //implementation("org.xerial:sqlite-jdbc:3.46.1.0")
        RawDatabase.main(args);
        System.out.println();

        System.out.println("==========Hibernate==========");
        //implementation("org.xerial:sqlite-jdbc:3.46.1.0")
        //implementation("com.github.gwenn:sqlite-dialect:0.1.4")
        //implementation("org.hibernate:hibernate-core:5.5.7.Final")
        Hibernate.main(args);
        System.out.println();
    }
}
