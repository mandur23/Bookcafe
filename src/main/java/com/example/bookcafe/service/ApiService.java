package com.example.bookcafe.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApiService {
    public static void main(String[] args) {
        String apiKey = "ttbmamy55881552001";  // 발급받은 API 키를 여기에 입력하세요
        String query = "파이썬";  // 검색어
        int maxResults = 1000;  // 최대 검색 결과 수
        int start = 1;  // 검색 시작 위치

        HttpURLConnection connection = null;

        try {
            // API 호출 URL 생성
            String apiUrl = String.format(
                    "https://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey=%s&Query=%s&MaxResults=%d&start=%d&SearchTarget=Book&output=js",
                    apiKey, query, maxResults, start
            );

            // URL 객체 생성
            URL url = new URL(apiUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // 응답 코드 확인
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 성공적으로 응답을 받은 경우
                StringBuilder response = new StringBuilder();
                String inputLine;

                // 응답 데이터를 읽어들이기
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }

                // 응답 출력 (디버깅 용도)
                System.out.println("응답 내용: " + response.toString());

                // JSON 파싱
                JSONObject jsonResponse = new JSONObject(response.toString());

                // 오류 코드 확인
                if (jsonResponse.has("errorCode")) {
                    System.out.println("오류 코드: " + jsonResponse.getInt("errorCode"));
                    System.out.println("오류 메시지: " + jsonResponse.getString("errorMessage"));
                } else {
                    // "item" 배열 가져오기
                    JSONArray itemArray = jsonResponse.getJSONArray("item");

                    // "item" 배열이 null인지 확인
                    if (itemArray == null) {
                        System.out.println("'item' 배열이 없습니다. 응답을 확인하세요.");
                    } else {
                        // 세부 정보 출력
                        for (int i = 0; i < itemArray.length(); i++) {
                            JSONObject item = itemArray.getJSONObject(i);
                            System.out.println("Title: " + item.getString("title"));
                            System.out.println("Author: " + item.getString("author"));
                            System.out.println("Publisher: " + item.getString("publisher"));
                            System.out.println("Pub Date: " + item.getString("pubDate"));
                            System.out.println("Price: " + item.getInt("priceStandard") + "원");
                            System.out.println("--------------------------------------");
                        }
                    }
                }

            } else {
                System.out.println("API 호출 실패. 응답 코드: " + responseCode);
            }

        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
