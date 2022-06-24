package bstorm.akimts.oo.avance.exo;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReporterClass {

    private File inOut;

    public ReporterClass(String path) {
        this.inOut = new File(path);
    }

    // format .csv Commat Separated Values
    public void ToWrite(Sportif toWrite){

        if(toWrite == null)
            throw new IllegalArgumentException("arg should not be null");

        try ( PrintStream ps = new PrintStream(new FileOutputStream(inOut, true)) ) {
            ps.printf("\n%s,%s,%3$td-%3$tm-%3$tY",toWrite.getNom(), toWrite.getPrenom(), toWrite.getAnneeNaiss());
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public List<Sportif> read() throws Exception{

        List<Sportif> list = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(inOut)) ) {

            br.readLine();
            String line = null;
            while((line = br.readLine()) != null ){

                Sportif p = convert(line);
                list.add(p);

            }
            return list;

        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        throw new Exception("un problème a eut lieu lors de la lecture du fichier");

    }

    private Sportif convert(String aConvertir) throws Exception{

        String[] data = aConvertir.split(",");

        if( data.length != 3 )
            throw new Exception("erreur de conversion");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // 22-06-2022
        LocalDate date = LocalDate.parse(data[2], formatter);
        return new Sportif(data[0], data[1], date);

    }

}
}
