package com.apisul.util;

import com.apisul.model.Elevador;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.InputStream;
import java.util.List;

public class JsonValidator {

    private static final Dotenv dotenv = Dotenv.load();
    private static final String JSON_PATH = dotenv.get("JSON_PATH");

    public static List<Elevador> carregarElevadores() {
        try (InputStream is = JsonValidator.class.getResourceAsStream(JSON_PATH)) {

            if (is == null) {
                throw new RuntimeException("Arquivo " + JSON_PATH + " não encontrado no classpath.");
            }

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(is);
            if (!root.isArray()) {
                throw new RuntimeException("Formato inválido: JSON não é um array.");
            }

            try (InputStream is2 = JsonValidator.class.getResourceAsStream(JSON_PATH)) {
                return mapper.readValue(is2, new TypeReference<List<Elevador>>() {});
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar dados do JSON: " + e.getMessage(), e);
        }
    }
}
