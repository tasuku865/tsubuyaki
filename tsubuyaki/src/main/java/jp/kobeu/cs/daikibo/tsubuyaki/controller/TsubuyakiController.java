package jp.kobeu.cs.daikibo.tsubuyaki.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.kobeu.cs.daikibo.tsubuyaki.entity.Tsubuyaki;
import jp.kobeu.cs.daikibo.tsubuyaki.service.TsubuyakiService;

@Controller
public class TsubuyakiController {

  @Autowired
  TsubuyakiService ts;

  // タイトル画面を表示
  @GetMapping("/")

  String showIndex() {

    return "index";

  }

  // メイン画面を表示
  @GetMapping("/read")
  String showTsubuyakiList(@RequestParam(name = "keyword", required = false) String keyword, Model model) {

    List<Tsubuyaki> list = new ArrayList<Tsubuyaki>();
    if (keyword != null) {
      list = ts.getTsubuyakiByKeyword(keyword);
    } else {
      list = ts.getAllTsubuyaki(); // 全つぶやきを取得
    }

    model.addAttribute("tsubuyakiList", list); // モデル属性にリストをセット

    model.addAttribute("tsubuyakiForm", new TsubuyakiForm()); // 空フォームをセット

    model.addAttribute("keywordForm", new KeywordForm());

    return "tsubuyaki_list"; // リスト画面を返す

  }

  // つぶやきを投稿

  @PostMapping("/read")
  String postTsubuyaki(@ModelAttribute("tsubuyakiForm") TsubuyakiForm form, Model model) {

    // フォームからエンティティに移し替え

    Tsubuyaki t = new Tsubuyaki();

    t.setName(form.getName());

    t.setComment(form.getComment());

    // サービスに投稿処理を依頼

    ts.postTsubuyaki(t);

    return "redirect:/read"; // メイン画面に転送

  }

}
