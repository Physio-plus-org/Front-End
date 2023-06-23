package com.example.physio_plus_app.Utils.HttpHandler.PSF;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public abstract class PSFRegisterCenterHandler extends PSFHandler {
    private static final String FOLDER_NAME = "R1/";
    private static final String FILE_NAME = "r1.php";

    public static String request(String namePhysio, String addressPhysio, String afmPhysio) throws Exception {
        RequestBody body = new FormBody.Builder()
                .add("tax_id_number", afmPhysio)
                .add("name", namePhysio)
                .add("address",addressPhysio)
                .build();
       return psfRequest(FOLDER_NAME+FILE_NAME, body);
    }
}
