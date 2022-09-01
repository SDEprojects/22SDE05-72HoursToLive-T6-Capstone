package main.java.controller;

public class Response {
    private boolean isValid;
    private String verb;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNoun() {
        return noun;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    private String location;
    private String noun;

    public Response(String verb, String location, String noun) {
        this.verb = verb;
        this.location = location;
        this.noun = noun;
        if (verb == null || verb.length() == 0){
            isValid = false;
        }else{
            isValid = true;
        }
    }

    @Override
    public String toString() {
        return "Response{" +
                "isValid=" + isValid +
                ", verb='" + verb + '\'' +
                ", location='" + location + '\'' +
                ", noun='" + noun + '\'' +
                '}';
    }


}
