package sample;

import com.aafanasev.fonoapi.DeviceEntity;
import com.aafanasev.fonoapi.retrofit.FonoApiFactory;
import com.aafanasev.fonoapi.retrofit.FonoApiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import jess.JessException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.module.Jess;

import java.io.*;
import java.net.http.HttpClient;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Controller {
    @FXML
    GridPane GridU;
    @FXML
    TextField BrandTextF, BudgetTextF, BatteryTextF, WeightTextF, YearTextF, SizeTextF;
    @FXML
    Button SearchButton;
    final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();


//    HostConfiguration config = client.getHostConfiguration();
//        config.setProxy(PROXY_HOST, PROXY_PORT);
//
//    String username = "guest";
//    String password = "s3cr3t";
//    Credentials credentials = new UsernamePasswordCredentials(username, password);
//    AuthScope authScope = new AuthScope(PROXY_HOST, PROXY_PORT);
//
//        client.getState().setProxyCredentials(authScope, credentials);
//     AsyncHttpClient c = asyncHttpClient(config().setProxyServer(proxyServer("127.0.0.1", 38080)));

    public static ObservableList<Mobile> mobiles_list;


    public void initialize() throws JessException, FileNotFoundException {
        Jess.resetter();
        FileInputStream input = new FileInputStream("src/maxresdefault2 .jpg");


        Image image = new Image(input);


        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Background background = new Background(backgroundimage);
        GridU.setBackground(background);
    }

    @FXML
    public void updateDatabase() {
        System.out.println("update database");
        try {

            String TOKEN = "82b2becb8c4ad2a1bf244dd51578a4b17bcf2a13098c43b6";
            FonoApiService api = new FonoApiFactory().create();

            api.getLatest(TOKEN, "", 100).enqueue(new Callback<List<DeviceEntity>>() {
                @Override
                public void onResponse(Call<List<DeviceEntity>> call, Response<List<DeviceEntity>> response) {
                    try {
                        FileWriter fw = new FileWriter("/home/sengh/IdeaProjects/MPExS/src/facts.clp");
                        fw.write("(deffacts phoneslist\n");
                        response.body().forEach(device -> {
                            try {
                                fw.write("\t(phone(brand " + setBrand(device.getBrand()).toLowerCase() + ")(price " + setPrice() + ")(battery " + setBattery(device.getBattery_c()) + ")(model \"" + device.getDeviceName() + "\")(weight " + setWeight(device.getWeight()) + ")(year " + setYear(device.getAnnounced()) + ")(size " + setDeviceSize(device.getSize()) + "))\n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        fw.write("\n" +
                                "    )\n" +
                                "\n" +
                                "(reset)\n" +
                                "(run)");
                        fw.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<List<DeviceEntity>> call, Throwable t) {
                    System.out.println(t.fillInStackTrace());
                }
            });
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @FXML
    public void Search() throws JessException, IOException {
        mobiles_list = FXCollections.observableArrayList();
        String brand = (!BrandTextF.getText().isEmpty()) ? BrandTextF.getText() : "nil",
                budget = (!BudgetTextF.getText().isEmpty()) ? BudgetTextF.getText() : "10000000",
                battery = (!BatteryTextF.getText().isEmpty()) ? BatteryTextF.getText() : "0",
                weight = (!WeightTextF.getText().isEmpty()) ? WeightTextF.getText() : "10000",
                year = (!YearTextF.getText().isEmpty()) ? YearTextF.getText() : "2000",
                size = (!SizeTextF.getText().isEmpty()) ? SizeTextF.getText() : "0";

        Jess.rete.eval("(assert (inputdata(brand " + brand + ")(price " + budget + ")(battery " + battery + ")(weight "+ weight+ ")(year "+ year +")(size "+size + ")))");
//        Jess.rete.eval("(deffunction getresults () " +
//                "    (return (result1(brand ?brand) (price ?price) (battery ?battery) (model ?model) (weight ?weight) (year ?year) (size ?size))))");
        Jess.rete.eval("(defrule findphone "
                + "(inputdata (brand ?brandchosen) (price ?pricechosen) (battery ?batterychosen) (weight ?weightchosen) (year ?yearchosen) (size ?sizechosen))"
                + "(phone (brand ?brand) (price ?price) (battery ?battery) (model ?model) (weight ?weight) (year ?year) (size ?size))"
                + "(or (test (= ?brand ?brandchosen)) (test (= ?brandchosen nil)))"
                + "(test (> ?battery ?batterychosen))"
                + "(test (< ?price ?pricechosen))"
                + "(test (< ?weight ?weightchosen))"
                + "(test (>= ?year ?yearchosen))"
                + "(test (>= ?size ?sizechosen))"
                + "=> (printout t ?brand crlf) (printout t ?model crlf) (printout t ?size crlf) (printout t ?battery crlf) (printout t ?weight crlf) (printout t ?price crlf))");

        Reader result = new StringReader(Jess.runner());
        BufferedReader reader = new BufferedReader(result);
        String resBrand = reader.readLine(), resModel = reader.readLine(), resSize = reader.readLine(), resBattery = reader.readLine(), resWeight = reader.readLine(), resPrice = reader.readLine();
        while (resBrand != null) {
            mobiles_list.add(new Mobile(resBrand, resModel, resSize, resBattery, resWeight, resPrice));
            resBrand = reader.readLine();
            resModel = reader.readLine();
            resSize = reader.readLine();
            resBattery = reader.readLine();
            resWeight = reader.readLine();
            resPrice = reader.readLine();
        }
        SearchButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("Result.fxml")));
    }

    private String setBrand(String brand) {
        if (brand == null)
            return "Generic";
        return brand;

    }

    private String setPrice() {
        return Integer.toString((int) ((Math.random() * ((80000 - 10000) + 1)) + 10000));
    }

    private String setBattery(String battery) {
        Pattern pattern = Pattern.compile("(\\d{4})");
        Matcher matcher = pattern.matcher(battery);

        while (matcher.find()) {
            return matcher.group();
        }
        return Integer.toString((int) ((Math.random() * ((4000 - 1500) + 1)) + 1000));


    }

    private String setDeviceSize(String size)
    {
        Pattern pattern =  Pattern.compile(".[0-9.]+");
        Matcher matcher = pattern.matcher(size);

        while(matcher.find())
        {
            return matcher.group();
        }
        return "5";
    }

    private String setWeight(String weight)
    {
        Pattern pattern =  Pattern.compile(".[0-9]+");
        Matcher matcher = pattern.matcher(weight);

        while(matcher.find())
        {
            return matcher.group();
        }
        return "150";

    }

    private String setYear(String year)
    {
        Pattern pattern = Pattern.compile("(\\d{4})");
        Matcher matcher = pattern.matcher(year);

        while(matcher.find())
        {
            return matcher.group();
        }
        return "2019";
    }




}