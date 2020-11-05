package Request;

import java.lang.Integer;
import java.net.http.*;
import java.util.ArrayList;
import java.io.IOException;
import java.net.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import Pais.Pais;
import Medicao.Medicao;

public class Request {

    /**
     * Retorna lista com todos os paises da requisição
     * @return ArrayList<Pais>
     * @throws IOException
     * @throws InterruptedException
     */
    public static ArrayList<Pais> countryRequest() throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .header("accepted", "json/aplication")
            .uri(URI.create("https://api.covid19api.com/countries"))
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String jsonString = response.body();
        ArrayList<Pais> countryList = new ArrayList<Pais>();

        try{
            JSONParser parser = new JSONParser();
            Object resultObject = parser.parse(jsonString); 
            
            
            if(resultObject instanceof JSONArray){
                JSONArray objectList = (JSONArray)resultObject;
                for(Object country : objectList){
                    JSONObject currObject = (JSONObject)country;

                    Pais currCountry = new Pais();
                    currCountry.setNome(currObject.get("Country").toString());
                    currCountry.setSlug(currObject.get("Slug").toString());
                    currCountry.setCodigo(currObject.get("ISO2").toString()); 
                    
                    countryList.add(currCountry);
                }
            }
            
        }catch(ParseException e){
            System.out.println(e);
        }

        return countryList;
    }

    /**
     * Retorna lista de Medições de um Pais em um periodo especificado com um tipo de caso especifico
     * Datas no formato ex: 2020-03-01
     * nomePais "slug" do pais
     * status "confirmed", "recovered", "deaths"
     * @param nomePais
     * @param status
     * @param primeiraData
     * @param ultimaData
     * @param pais
     * @return ArrayList<Medicao>
     * @throws IOException
     * @throws InterruptedException
     */
    public static ArrayList<Medicao> casesByCountry(String nomePais, String status, String primeiraData, String ultimaData, Pais pais) throws IOException, InterruptedException {
        ArrayList<Medicao> list = new ArrayList<Medicao>();

        String URL = "https://api.covid19api.com/country/" + nomePais + "/status/" + status + "?from=" + primeiraData + "T00:00:00Z" + "&to=" + ultimaData + "T00:00:00Z";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .header("accepted", "json/aplication")
            .uri(URI.create(URL))
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String jsonString = response.body();

        try{
            JSONParser parser = new JSONParser();
            Object resultObject = parser.parse(jsonString);
            if(resultObject instanceof JSONArray){
                JSONArray objectList = (JSONArray)resultObject;

                for(Object object : objectList){
                    JSONObject currObject = (JSONObject)object;

                    Medicao currMed = new Medicao();

                    currMed.setPais(pais);
                    currMed.setCasos(Integer.parseInt(currObject.get("Cases").toString()));
                    currMed.setData(currObject.get("Date").toString());

                    list.add(currMed);
                }

            }


        }catch(Exception e){
            System.out.println(e);
        }

        return list;
    }

}
