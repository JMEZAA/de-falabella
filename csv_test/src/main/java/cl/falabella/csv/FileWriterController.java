package cl.falabella.csv;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileWriterController {
	
@Value("gs://de-falabella/CSV_INPUT/GlobalLandTemperaturesByMajorCity.csv")
private Resource gcsFile;

	/*Metodo_para_filtrar_la_lista*/
	public static List<archivo> ListaFiltrada(List<archivo> list, String filtro) {
	return list.stream().filter(x -> x.Item5().equals(filtro)).collect(Collectors.toList());
	}

	public static String MD5(String md5) {
	    try {
	        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(md5.getBytes());
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < array.length; ++i) {
	            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	        }
	        return sb.toString();
	    } catch (java.security.NoSuchAlgorithmException e) {
	        System.out.println(e.getMessage());
	    }
	    return null;
	}

	@RequestMapping(value ="/", method = RequestMethod.GET)	
	public void csvFile() throws IOException {

		BufferedReader br = null;
		BufferedReader lr = null;
		String SEPARATOR = ",";
		String line = "";
		FileWriter fichero = null;
		PrintWriter pw = null;

		ArrayList<String> al = new ArrayList<>();
		ArrayList<archivo> lista = new ArrayList<>();
		archivo cabecera = new archivo();
		
        try {
            
        	br = new BufferedReader(new InputStreamReader( gcsFile.getInputStream()));
            lr = br;
            while((line = br.readLine()) != null){ 
                String[] datos = line.split(SEPARATOR);                
                archivo arc = new archivo();   

                if (datos[0].equals("dt")) {
                	cabecera.Item1(datos[0]);
                	cabecera.Item2(datos[1]);
                	cabecera.Item3(datos[2]);
                	cabecera.Item4(datos[3]);
                	cabecera.Item5(datos[4]);
                	cabecera.Item6(datos[5]);
                	cabecera.Item7(datos[6]);
                } else { 
                	arc.Item1(datos[0]);
                        arc.Item2(datos[1]);
                        arc.Item3(datos[2]);
                        arc.Item4(datos[3]);
                        arc.Item5(datos[4]);
                        arc.Item6(MD5(datos[5]));
                        arc.Item7(MD5(datos[6]));
                        lista.add(arc);
                    
                    if (al.indexOf(datos[4]) == -1) { 
                        al.add(datos[4]);                    
                    }
                }
            } 
            
            /*try (OutputStream os = ((WritableResource) gcsFile2).getOutputStream()) {
            	  os.write("fxx".getBytes());
            	}*/
            
            for(String a : al) {
            	try {
            		               
            		fichero = new FileWriter("Archivo_Pais_" + a + ".csv");
            		pw = new PrintWriter(fichero);  
            		
            		List<archivo> lstfilt = ListaFiltrada(lista, a);
            		pw.println(cabecera.Item1() + "," + cabecera.Item2() + "," + cabecera.Item3() + "," + cabecera.Item4() + "," + cabecera.Item5() + "," + cabecera.Item6() + "," + cabecera.Item7());
            		
            		for(archivo l : lstfilt) {
            			pw.println(l.Item1() + "," + l.Item2() + "," + l.Item3() + "," + l.Item4() + "," + l.Item5() + "," + l.Item6() + "," + l.Item7());
            		}
            		
            		pw.close();
            	}
            	catch(IOException ex) {
            		System.out.println(ex.getMessage());
            	}
            	
            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (null!=br) {
                br.close();
            }
        }

	}
}