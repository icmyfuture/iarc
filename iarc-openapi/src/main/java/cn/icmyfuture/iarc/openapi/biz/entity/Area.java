package cn.icmyfuture.iarc.openapi.biz.entity;

public class Area  {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Area(String name){
        this.name = name;
    }

    public Area(){
        this(null);
    }
}
