package com.example.GroupDemo.Controller;

import org.apache.commons.codec.net.URLCodec;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class GroupDemoController {

    @GetMapping("/share")
    public String share() {
        return "share";
    }

    public static String checkUuid(String str) {
        return str;
    }

    @PostMapping("/url")
    public String url(@RequestParam(name="name") String name,
                      Model model) {

        String inviteCode = name; // 招待コード

        String randomString;
        do {
            //ランダムな文字列を生成
            randomString = generateRandomString();
            System.out.println(randomString);
        } while (checkUuid(randomString) == null); //テーブルに文字列が存在しなければループを抜ける //checkUuidでテーブルにランダムな文字列が存在しないか確認する


        // URLエンコーダーを作成
        //URLCodec urlCodec = new URLCodec();

        String inviteLink = null;
        try {
            // 招待コードをURLエンコードする
            // String encodedInviteCode = urlCodec.encode(inviteCode);

            // 遷移先のURLを作成
            // inviteLink = "http://comunity?code=" + encodedInviteCode;

            model.addAttribute("randomString", randomString);
            model.addAttribute("code", name);

            // System.out.println("遷移先URL: " + inviteLink);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "url";

    }

    @GetMapping("/comunity/{randomString}/{code}")
    public String share(@PathVariable("randomString") String str,
                        @PathVariable("code") String code,
                        Model model) {
        System.out.println(code);
        model.addAttribute("code", code);
        return "comunity";
    }

    public static String generateRandomString() {
        UUID uuid = UUID.randomUUID();
        String randomString = uuid.toString().replace("-", "");
        return randomString;
    }

    @GetMapping("/list")
    public String list() {
        return "list";
    }
}
