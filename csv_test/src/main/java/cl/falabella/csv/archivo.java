package cl.falabella.csv;

public class archivo {
    
    private String item1;   
    private String item2;
    private String item3;
    private String item4;
    private String item5;
    private String item6;
    private String item7;    
    
    public void Item1(String item){
        this.item1 = item;
    }
    
    public void Item2(String item){
        this.item2 = item;
    }
    
    public void Item3(String item){
        this.item3 = item;
    }
    
    public void Item4(String item){
        this.item4 = item;
    }
    
    public void Item5(String item){
        this.item5 = item;
    }
    
    public void Item6(String item){
        this.item6 = item;
    }
    
    public void Item7(String item) {
        this.item7 = item;
    }
    
    public String Item1() {
    	return item1;
    }
    
    public String Item2() {
    	return item2;
    }
    
    public String Item3() {
    	return item3;
    }
    
    public String Item4() {
    	return item4;
    }
    
    public String Item5() {
    	return item5;
    }
    
    public String Item6() {
    	return item6;
    }
    
    public String Item7() {
    	return item7;
    }
    
    // Constructor
    public archivo() {
    	this.item1 = "";
    	this.item2 = "";
    	this.item3 = "";
    	this.item4 = "";
    	this.item5 = "";
    	this.item6 = "";
    	this.item7 = "";
    }
}