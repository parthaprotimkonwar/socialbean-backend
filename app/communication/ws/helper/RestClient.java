package communication.ws.helper;

import application.exceptions.BaseException;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.F;
import play.libs.Json;
import play.libs.WS;

/**
 * Created by pkonwar on 1/29/2017.
 */
public class RestClient {

    public static F.Promise<WS.Response> sendRequest(RestClientEndpointInterface restClientProtocol, String... params)
            throws BaseException {
        if (!HttpRequestType.GET.equals(restClientProtocol.getReqType())) {
            throw new BaseException("1001", "Requests other than GET requires reqNode");
        }
        WS.WSRequestHolder requestHolder = getFinalUrl(restClientProtocol, params);
        F.Promise<WS.Response> res = requestHolder.get();

        return res;
    }

    public static F.Promise<WS.Response> sendRequest(RestClientEndpointInterface restClientProtocol, JsonNode reqNode, String... params)
            throws BaseException {
        WS.WSRequestHolder requestHolder = getFinalUrl(restClientProtocol, params);
        F.Promise<WS.Response> res = null;
        switch (restClientProtocol.getReqType()) {
            case POST:
                res = requestHolder.post(reqNode);
                break;
            case PUT:
                res = requestHolder.put(reqNode);
                break;
        }
        return res;
    }

    public static F.Promise<WS.Response> sendRequest(RestClientEndpointInterface restClientProtocol, Object reqObject, String... params)
            throws BaseException {
        return sendRequest(restClientProtocol, Json.toJson(reqObject), params);
    }

    private static WS.WSRequestHolder getFinalUrl(RestClientEndpointInterface restClientProtocol, String[] paramsValue)
            throws BaseException {
        String[] params = restClientProtocol.getParams();
        /*if (params.length > paramsValue.length) {
            throw new BaseException("1001", "Less num of params passed for " + restClientProtocol.getName() + " : req=" + params.length + ", passed=" + paramsValue.length);
        }*/
        String url = restClientProtocol.getUrl();
        WS.WSRequestHolder finalUrl = WS.url(url);
        /*if (params.length > 0) {
            for (int i = 0; (i < params.length) && (i < paramsValue.length); i++) {
                finalUrl = finalUrl.setQueryParameter(params[i], paramsValue[i]);
            }
        }*/
        return finalUrl;
    }
}
