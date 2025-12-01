package main;


public class Vie {
    private int pv;
    private int pv_Max;
    
    public Vie(int pv) {
        this.pv = pv;
        this.pv_Max = pv;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        if (pv<0) this.pv=0;
        else if (pv>pv_Max) this.pv=pv_Max;
        else this.pv=pv;
    }

    public void removePv(int i){
        setPv(pv-i);

    }

    public void addPv(int i){
        pv+=i;
        pv_Max+=i;
    }

    public int getPvMax() {
        return this.pv_Max;
    }


    public String affiche_pv(){
        String res = this.pv + "/" + this.pv_Max;
        return res;
    }
    
}
