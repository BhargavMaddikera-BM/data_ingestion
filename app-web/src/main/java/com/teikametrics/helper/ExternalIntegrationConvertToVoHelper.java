package com.teikametrics.helper;

public class ExternalIntegrationConvertToVoHelper {

	private static ExternalIntegrationConvertToVoHelper settingsConvertToVoHelper;

	public static ExternalIntegrationConvertToVoHelper getInstance() {
		if (settingsConvertToVoHelper == null) {
			settingsConvertToVoHelper = new ExternalIntegrationConvertToVoHelper();
		}
		return settingsConvertToVoHelper;
	}







}
