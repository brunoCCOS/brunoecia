// import Estatistica.Estatistica;
import Medicao.Medicao;
// import Pais.Pais;
// import StatusCaso.StatusCaso;
import Request.Request;
import Pais.Pais;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        try{
            ArrayList<Pais> lista = Request.countryRequest();

            Pais paisEscolhido = lista.get(0);
            String slug = paisEscolhido.getSlug();
            String status = "confirmed";
            String primeiraData = "2020-03-01";
            String ultimaData = "2020-04-01";

            ArrayList<Medicao> listaMed = Request.casesByCountry(slug, status, primeiraData, ultimaData, paisEscolhido);

            for(Medicao x : listaMed){
                System.out.println(x.getPais().getNome());
                System.out.println(x.getCasos());
                System.out.println(x.getData());
            }
            
        }catch(Exception e){
            System.out.println(e);
        }

        
        
    }
}
