package br.com.networcCalculator.calculation;

import java.math.BigDecimal;
import java.net.NetPermission;

public class NetworkCalculation {

	private int n1, n2, n3, n4, mask, idMask;
	String inverse, binMask;
    String m1, m2, m3, m4;
	
	public NetworkCalculation(int n1, int n2, int n3, int n4, int mask){
		this.n1 = n1;
		this.n2 = n2;
		this.n3 = n3;
		this.n4 = n4;
		this.mask = mask;
	}
	
	public BigDecimal calcularQtdHosts(int masc){
        masc = 32 - masc;
        
        double qtd = Math.pow(2, masc);
        
        double hosts = qtd-2;
        
        BigDecimal bigHosts = new BigDecimal(hosts);
        System.out.println("Nº total de hosts: "+bigHosts);
        
        return bigHosts;
        
    }
    
    public String calcularMascara(int mask){
        m1="0"; m2="0"; m3="0"; m4="0";
        double parte = mask/8.0;
        
        if(parte> 0 && parte<=1){
           m1 = calculatePartDifferenteMask(mask, m1);
           idMask=1;
        }else if(parte>1 && parte<=2){
           m1 = "255"; 
           mask -= 8;
           m2 = calculatePartDifferenteMask(mask, m2);
           idMask=2;
        }else if(parte>2 && parte<=3){
           m1 = "255";
           m2 = "255";
           mask -= 16;
           m3 = calculatePartDifferenteMask(mask, m3);
           idMask=3;
        }else if(parte>3 && parte<=4){
           m1 = "255";
           m2 = "255";
           m3 = "255";
           mask -= 24;
           m4 = calculatePartDifferenteMask(mask, m4);
           idMask=4;
        }else{
            System.out.println("Invalide Mask!");
        }
        
        String resultado = m1+"."+m2+"."+m3+"."+m4;
        
        System.out.println("Máscara: "+resultado);
        return resultado;
    }
    
    public String calculatePartDifferenteMask(int masc, String m){
        m = "1";
        inverse = "0";
        int temp;
            for(int i=2; i<=8; i++){
               if(i<=masc){
                   m+="1";
                   inverse+="0";
               }else{
                   m+="0";
                   inverse+="1";
               }
           }
           
           binMask = m;
           temp = Integer.parseInt(m,2);           
           m = Integer.toString(temp);
           
           return m;
    }
    
    public String[] calcularIp(){
        String temp;
        String rede="0.0.0.0", broadcast="0.0.0.0";
        String[] ip = new String[2];
        
        switch(idMask){
            case 1:
                temp = Integer.toString(n1, 2);
                rede=calculoRede(temp)+".0.0.0";
                broadcast=calculoBroad(temp)+".255.255.255";
                break;
            case 2:
                temp = Integer.toString(n2, 2);
                rede=n1+"."+calculoRede(temp)+".0.0";
                broadcast=n1+"."+calculoBroad(temp)+".255.255";
                break;
            case 3:
                temp = Integer.toString(n3, 2);
                rede=n1+"."+n2+"."+calculoRede(temp)+".0";
                broadcast=n1+"."+n2+"."+calculoBroad(temp)+".255";
                break;
            case 4:
                temp = Integer.toString(n4, 2);
                rede=n1+"."+n2+"."+n3+"."+calculoRede(temp);
                broadcast=n1+"."+n2+"."+n3+"."+calculoBroad(temp);
                break;
            default:
        }
        
        System.out.println("IP rede: "+rede);
        System.out.println("IP broadcas: "+broadcast);
        
        ip[0] = rede;
        ip[1] = broadcast;
        
        return ip;        		
    }
    
    public int calculoRede(String s){
        String rede = Integer.toBinaryString(Integer.parseInt(s,2) & Integer.parseInt(binMask,2));
        int nRede=Integer.parseInt(rede,2);
        return nRede;    
    }
    
    public int calculoBroad(String s){
        String broadcast = Integer.toBinaryString(Integer.parseInt(s,2) | Integer.parseInt(inverse,2));
        int nBroad = Integer.parseInt(broadcast,2);
        return nBroad;
    }
    
    public boolean hasConstraint(){
    	
    	boolean control = true;
    	
    	if(n1==0){
    		
    	}else if(n1==255){
    		
    	}else if(n1==127){
    		
    	}else{
    		control = false;
    	}
    	
    	return control;
    	
    }
	
}
