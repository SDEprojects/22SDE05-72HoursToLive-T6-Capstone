package main.java.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextParser {
    @Override
    public String toString() {
        return "TextParser{" +
                "commandMap=" + commandMap +
                ", locationMap=" + locationMap +
                ", itemMap=" + itemMap +
                '}';
    }

    private HashMap<String, HashSet<String>> commandMap;
    private HashMap<String, HashSet<String>> locationMap;
    private HashMap<String, HashSet<String>> itemMap;


    public TextParser() throws IOException {
        //ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, HashSet<String>>> typeRef = new TypeReference<HashMap<String, HashSet<String>>>() {};
        commandMap = new ObjectMapper().readValue(new File("actions.json"), typeRef);

        itemMap = new ObjectMapper().readValue(new File("items.json"), typeRef);
        locationMap = new ObjectMapper().readValue(new File("src/main/resources/locations.json"), typeRef);
    }

    public Response getCommands(String command){
        String[] args = command.toLowerCase().replaceAll("the", "").trim().split("\\s+");
//        System.out.println(Arrays.toString(args));

        if (args.length < 2){
            return new Response(null, null, null);
        }

        String verbInput = args[0];

        String nounInput = args.length == 2 ? args[args.length-1] : args[args.length-2] + args[args.length-1];


        String verbResponse = null;
        for (String key : commandMap.keySet()){
            Set<String> verbs = commandMap.get(key);
            for (String str : verbs) {
                if (verbInput.contains(str)) {
                    verbResponse = key;
                }
            }
        }

        String locationResponse = "";
        for (String key : locationMap.keySet()){
            Set<String> locations = locationMap.get(key);
            for (String loc : locations) {
                if (nounInput.contains(loc)) {
                    locationResponse = key;
                }
            }
        }

        String itemResponse = "";
        for (String key : itemMap.keySet()){
            Set<String> items = itemMap.get(key);
            for (String item : items) {
                if (nounInput.contains(item)) {
                    itemResponse= key;
                }
            }
        }

        return new Response(verbResponse, locationResponse, itemResponse);
    }


    public static void main(String[] args) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, HashSet<String>> actionMap = new HashMap<>();
        actionMap.put("go", Stream.of("walk", "run", "go")
                .collect(Collectors.toCollection(HashSet::new)));
        actionMap.put("pick up", Stream.of("pick", "pickup", "pick up")
                .collect(Collectors.toCollection(HashSet::new)));
        actionMap.put("attack", Stream.of("beat", "attack")
                .collect(Collectors.toCollection(HashSet::new)));
        actionMap.put("retreat", Stream.of("go back", "retreat")
                .collect(Collectors.toCollection(HashSet::new)));
        actionMap.put("use", Stream.of("heal", "use")
                .collect(Collectors.toCollection(HashSet::new)));

        mapper.writeValue(
                new FileOutputStream("actions.json"), actionMap);

        HashMap<String, HashSet<String>> locationMap = new HashMap<>();
        locationMap.put("great room", Stream.of("greatroom", "great room")
                .collect(Collectors.toCollection(HashSet::new)));
        locationMap.put("corridor", Stream.of("corridor", "walkway")
                .collect(Collectors.toCollection(HashSet::new)));
        locationMap.put("North", Stream.of("north", "noth")
                .collect(Collectors.toCollection(HashSet::new)));
        locationMap.put("South", Stream.of("south")
                .collect(Collectors.toCollection(HashSet::new)));
        locationMap.put("East", Stream.of("east")
                .collect(Collectors.toCollection(HashSet::new)));
        locationMap.put("West", Stream.of("west")
                .collect(Collectors.toCollection(HashSet::new)));
        mapper.writeValue(
                new FileOutputStream("locations.json"), locationMap);

        HashMap<String, HashSet<String>> itemMap = new HashMap<>();
        itemMap.put("weapon", Stream.of("weapon", "arms", "gun")
                .collect(Collectors.toCollection(HashSet::new)));
        itemMap.put("health potion", Stream.of("health potion", "medicine", "potion", "health")
                .collect(Collectors.toCollection(HashSet::new)));
        itemMap.put("key", Stream.of("key")
                .collect(Collectors.toCollection(HashSet::new)));
        mapper.writeValue(
                new FileOutputStream("items.json"), itemMap);


        TextParser t1 = new TextParser();
        Response r1 = t1.getCommands("go to great Room");
        System.out.println(r1);
        System.out.println("--------------------------------------");
        Response r2 = t1.getCommands("use a      potion");
        System.out.println(r2);
        System.out.println("--------------------------------------");
        Response r3 = t1.getCommands("run to the East");
        System.out.println(r3);
        System.out.println("--------------------------------------");
        Response r4 = t1.getCommands("gibberish");
        System.out.println(r4);


    }

}
