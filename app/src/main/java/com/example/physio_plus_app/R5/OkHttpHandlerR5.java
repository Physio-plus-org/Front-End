package com.example.physio_plus_app.R5;

public abstract class OkHttpHandlerR5 {

    public static void setUsersInfo() {

//        OkHttpClient client = new OkHttpClient();
//
//        String url = "https://physioplus.000webhostapp.com/R5/logHistory.php";
//        FormBody.Builder builder = new FormBody.Builder();
//        RequestBody body = builder.build();
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            final String responseBody = response.body().string();
//            JSONArray jsonArray = new JSONArray(responseBody);
//            if (jsonArray.length() > 0) {
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                    String firstName = jsonObject.optString("first_name");
//                    String lastName = jsonObject.optString("last_name");
//                    String Amka = jsonObject.optString("ssrn");
//                    String address = jsonObject.optString("address");
//
//                    Patient user = new Patient(firstName, lastName, Amka, address);
//                    R5.userList.add(user);
//                }
//            }
//        } catch (IOException ignored) {
//
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }

    }
}
