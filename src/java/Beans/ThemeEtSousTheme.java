/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eva Stonem
 */
public class ThemeEtSousTheme {

    private String ISBNSousTheme;
    private List<String> nomSousThemeList;
    private String descriptionSousTheme;
    private String nomTheme;
    private String descriptionTheme;

    public ThemeEtSousTheme() {
        nomSousThemeList = new ArrayList<>();
    }

    public ThemeEtSousTheme(List<String> nomSousTheme, String descriptionSousTheme) {
        this.nomSousThemeList = nomSousTheme;
        this.descriptionSousTheme = descriptionSousTheme;
    }

    public ThemeEtSousTheme(List<String> nomSousTheme) {
        this.nomSousThemeList = nomSousTheme;
    }

    public ThemeEtSousTheme(String ISBNSousTheme, String nomTheme) {
        this();
        this.ISBNSousTheme = ISBNSousTheme;
        this.nomTheme = nomTheme;
    }

    public ThemeEtSousTheme(String ISBNSousTheme, List<String> nomSousTheme) {
        this.ISBNSousTheme = ISBNSousTheme;
        this.nomSousThemeList = nomSousTheme;
    }

//    public ThemeEtSousTheme(List<String> nomSousThemeList, String descriptionSousTheme) {
//        this.nomSousThemeList = nomSousThemeList;
//        this.descriptionSousTheme = descriptionSousTheme;
//    }

    public String getISBNSousTheme() {
        return ISBNSousTheme;
    }

    public List<String> getNomSousTheme() {
        return nomSousThemeList;
    }

    public String getDescriptionSousTheme() {
        return descriptionSousTheme;
    }

    public String getNomTheme() {
        return nomTheme;
    }

    public String getDescriptionTheme() {
        return descriptionTheme;
    }

    @Override
    public String toString() {
        return "nomSousTheme=" + nomSousThemeList + ", descriptionSousTheme=" + descriptionSousTheme + ", nomTheme=" + nomTheme + '}';
    }
    

}
