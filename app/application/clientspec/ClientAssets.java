package application.clientspec;

/**
 * Created by parthaprotimkonwar on 26/02/17.
 */
public class ClientAssets {

    private String clientName;
    private ClientOnboarded clientOnboarded;

    public ClientAssets(String clientName) {
        this.clientName = clientName;
        //the client name
        clientOnboarded = getClientOnboarded(clientName);
    }

    public String getImageNameForEmailHeader() {

        String imageName = null;
        switch (clientOnboarded) {
            case ALLIANZ:
                imageName = "allianz-front-page-logo-small.png";
                break;

            case CHRIST_UNIVERSITY:
                imageName = "christ_logo_small.jpg";
                break;

            case CONNSOCIO:
                imageName = "";
                break;

            default:
                imageName = "christ_logo_small.jpg";
                break;
        }
        return imageName;
    }

    //returns the selected client or the default client
    private ClientOnboarded getClientOnboarded(String clientName) {

        ClientOnboarded clientOnboarded = null;
        try{
            clientOnboarded = ClientOnboarded.valueOf(clientName);
        } catch (Exception ex) {
            clientOnboarded = ClientOnboarded.CONNSOCIO;
        }
        return clientOnboarded;
    }

    public static void main(String[] args) {
        ClientAssets assets = new ClientAssets("ALLIANZ");
        System.out.println(assets.getImageNameForEmailHeader());
    }
}
