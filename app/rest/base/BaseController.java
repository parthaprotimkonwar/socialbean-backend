package rest.base;

import application.exceptions.BaseException;
import application.exceptions.ErrorConstants;
import com.fasterxml.jackson.databind.JsonNode;
import rest.responsedto.ErrorResponse;
import org.apache.http.HttpStatus;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

public class BaseController extends Controller{

	/**
	 * Converts RequestBody to ObjectDto
	 * @param requestBody
	 * @param clazz
	 * @return
	 * @throws BaseException
	 */
	public <A> A convertRequestBodyToObject(RequestBody requestBody, Class<A> clazz) throws BaseException{
		try {
			JsonNode jsonNode = requestBody.asJson();
			return Json.fromJson(jsonNode, clazz);
		} catch (Exception ex) {
			ErrorConstants error = ErrorConstants.INVALID_REQUEST_DATA;
			throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
		}
	}

	public <A> A convertJsonNodeToObject(JsonNode jsonNode, Class<A> clazz) throws BaseException {
		try {
			return Json.fromJson(jsonNode, clazz);
		} catch (Exception ex) {
			ErrorConstants error = ErrorConstants.INVALID_REQUEST_DATA;
			throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
		}
	}
	/**
	 * Generate error which is unknown to system
	 * @return
	 */
	public ErrorResponse unknownErrorResponse() {
		ErrorConstants error = ErrorConstants.CONTACT_SYSTEM_ADMINISTRATOR;
		return new ErrorResponse(error.getErrorCode(), error.getErrorMessage());
	}

	/**
	 * Success Object in JSON.
	 * @param object
	 * @return
	 */
	public Result convertObjectToJsonResponse(Object object){
		JsonNode jsonNode = Json.toJson(object);
		addCORSHeader();
		return ok(jsonNode);
	}

	/**
	 * Add CORS Header
	 */
	public void addCORSHeader() {
		String origin = request().getHeader("Origin");
		if(origin != null)
			response().setHeader("Access-Control-Allow-Origin", origin);
	}
	/**
	 * Validation error to JSON response.
	 * @param object
	 * @return
	 */
	public Result validationErrorToJsonResponse(Object object){
		JsonNode jsonNode = Json.toJson(object);
		return status(HttpStatus.SC_BAD_REQUEST, jsonNode);
	}

	/**
	 * Finds ErrorType based on errorResponse and send specific response.
	 * Rules :
	 * 	ErrorCode Starts with ::
	 * 	4 : Validation Error
	 *  5 : Internal Server Error
	 * @param errorResponse
	 * @return
	 */
	public Result errorObjectToJsonResponse(ErrorResponse errorResponse){

		String errorCode = errorResponse.getErrorCode();
		char errorType = (errorCode == null || errorCode.trim().length() == 0) ? '4' : errorCode.charAt(0);

		//the httpErrorCode
		int httpErrorCode;

		switch (errorType) {
			case '4':
				httpErrorCode = HttpStatus.SC_BAD_REQUEST;
				break;

			case '5':
				httpErrorCode = HttpStatus.SC_INTERNAL_SERVER_ERROR;
				break;

			default:
				httpErrorCode = HttpStatus.SC_BAD_REQUEST;
				break;
		}
		addCORSHeader();
		return errorObjectToJsonResponse(httpErrorCode, errorResponse);
	}

	/**
	 * Error Object to JSON response
	 * @param httpErrorCode
	 * @param errorResponse
	 * @return
	 */
	public Result errorObjectToJsonResponse(int httpErrorCode, ErrorResponse errorResponse){
		JsonNode jsonNode = Json.toJson(errorResponse);
		addCORSHeader();
		return status(httpErrorCode, jsonNode);
	}
}