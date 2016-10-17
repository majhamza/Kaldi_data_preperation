
package kaldidatapreparation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author majhamza
 */
public class KaldiDataPreparation {

    //crée le fichier utt2spk
    public static void utt2spk(String folder) throws FileNotFoundException{
        
        File f=new File(folder);
        File[] lf=f.listFiles();
        LinkedList<String> ls=new LinkedList<>();
        // n contient la liste des dossiers des speakers
        String[] n=new String[lf.length];
        for(int i=0; i<lf.length; i++)
        {
         n[i]=lf[i].getName();
        }
       Arrays.sort(n);
//       for(int i=0; i<lf.length; i++)
//        System.out.println(n[i]);
       
       
       for(int i=0; i<lf.length; i++){
       File g=new File(folder+"/"+n[i]);
       File[] l=g.listFiles();
       
       // s contient les noms des fichiers audio d'un dossier
       String[] s=new String[l.length];
       for(int j=0; j<s.length; j++)
           s[j]=l[j].getName();
        Arrays.sort(s);
        for(int j=0; j<s.length; j++)
            ls.add(n[i]+"-"+s[j].substring(0,s[j].indexOf('.'))+" "+n[i]);
       }
       File t=new File(folder+"/utt2spk");
        PrintWriter p=new PrintWriter(t);
       for(int i=0; i<ls.size(); i++)
           p.println(ls.get(i));
       p.close();
    }
    
    public static void wavscp(String folder) throws FileNotFoundException{
    
        File f=new File(folder);
        File[] lf=f.listFiles();
        LinkedList<String> ls=new LinkedList<>();
        // n contient la liste des dossiers des speakers
        String[] n=new String[lf.length];
        for(int i=0; i<lf.length; i++)
        {
         n[i]=lf[i].getName();
        }
       Arrays.sort(n);
       //for(int i=0; i<lf.length; i++)
       // System.out.println(n[i]);
       
       
       for(int i=0; i<lf.length; i++){
       File g=new File(folder+"/"+n[i]);
       File[] l=g.listFiles();
       // s contient les noms des fichiers audio d'un dossier
       String[] s=new String[l.length];
       for(int j=0; j<s.length; j++)
           s[j]=l[j].getName();
        Arrays.sort(s);
        for(int j=0; j<s.length; j++)
            ls.add(n[i]+"-"+s[j].substring(0,s[j].indexOf('.'))+" "+folder+n[i]+"/"+s[j]);
       }
       File t=new File(folder+"/wav.scp");
        PrintWriter p=new PrintWriter(t);
       for(int i=0; i<ls.size(); i++)
           p.println(ls.get(i));
       p.close();
    
    }
    
    public static void uttid(String folder) throws FileNotFoundException{
    
        
        File f=new File(folder);
        File[] lf=f.listFiles();
        LinkedList<String> ls=new LinkedList<>();
        // n contient la liste des dossiers des speakers
        String[] n=new String[lf.length];
        for(int i=0; i<lf.length; i++)
        {
         n[i]=lf[i].getName();
        }
       Arrays.sort(n);
       //for(int i=0; i<lf.length; i++)
       // System.out.println(n[i]);
       
       
       for(int i=0; i<lf.length; i++){
       File g=new File(folder+"/"+n[i]);
       File[] l=g.listFiles();
       // s contient les noms des fichiers audio d'un dossier
       String[] s=new String[l.length];
       for(int j=0; j<s.length; j++)
           s[j]=l[j].getName();
        Arrays.sort(s);
        for(int j=0; j<s.length; j++)
            ls.add(n[i]+"-"+s[j].substring(0,s[j].indexOf('.'))+" ");
       }
       File t=new File(folder+"/uttids");
        PrintWriter p=new PrintWriter(t);
       for(int i=0; i<ls.size(); i++)
           p.println(ls.get(i));
       p.close();
        
    }
    
        public static void speakers(String folder) throws FileNotFoundException{
    
        
        File f=new File(folder);
        File[] lf=f.listFiles();
        LinkedList<String> ls=new LinkedList<>();
        // n contient la liste des dossiers des speakers
        String[] n=new String[lf.length];
        for(int i=0; i<lf.length; i++)
        {
         n[i]=lf[i].getName();
        }
       Arrays.sort(n);
       
       File t=new File(folder+"/spk2gender");
        PrintWriter p=new PrintWriter(t);
       for(int i=0; i<n.length; i++)
           p.println(n[i]);
       p.close();
        
    }
        
        
        public static void sox_convertion(String path) throws IOException, InterruptedException{
        Process p;
	    p = Runtime.getRuntime().exec("sox "+path+" -r 8000 "+path.substring(0,path.indexOf(".wav"))+"c.wav");
	    p.waitFor();
        }
        
        public static void batch_convertion(String wavscp) throws FileNotFoundException, IOException, InterruptedException{
        File f=new File(wavscp);
        Scanner s=new Scanner(f);
        while(s.hasNextLine()){
        String t=s.nextLine();
        Scanner s2=new Scanner(t);
        System.out.println(s2.next());
        sox_convertion(s2.next());
        }
        
        }

        
   
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
    //uttid("/home/majhamza/Téléchargements/Reco_locuteurs/Abdelghafour/");
   // wavscp("/home/majhamza/Téléchargements/Reco_locuteurs/Abdelghafour/");
   // utt2spk("/home/majhamza/Téléchargements/Reco_locuteurs/Abdelghafour/");
       //speakers("/home/majhamza/Téléchargements/Reco_locuteurs/Abdelghafour/");
     batch_convertion("/home/majhamza/Téléchargements/Reco_locuteurs/youssef_wav.scp");
    }
}
