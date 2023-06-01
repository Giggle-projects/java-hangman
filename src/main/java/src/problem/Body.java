package src.problem;

import java.util.ArrayList;
import java.util.List;

public enum Body implements Problem {

    EYES("eyes"), TEETH("teeth"), TOES("toes"), HEAD("head"), EYEBROW("eyebrow"),
    EARS("ears"), HAIR("hair"), SHOULDER("shoulder"), TONGUE("tongue"), BONES("bones"),
    HAND("hand"), FINGER("finger"), KNEE("knee"), MOUSTACHE("moustache"), ANKLE("ankle"),
    NOSE("node"), LEG("leg"), THUMB("thumb"), NECK("neck"), HEEL("heel");
    private final String name;

    Body(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public List<String> getNameList() {
        List<String> bodyList = new ArrayList<>();

        for (Body body : Body.values()) {
            bodyList.add(body.getName());
        }

        return bodyList;
    }
}
