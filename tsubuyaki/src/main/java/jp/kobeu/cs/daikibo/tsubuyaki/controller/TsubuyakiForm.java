package jp.kobeu.cs.daikibo.tsubuyaki.controller;

import lombok.Data;

@Data
public class TsubuyakiForm {

  String name; // 投稿者

  String comment; // つぶやき（省略不可）

}