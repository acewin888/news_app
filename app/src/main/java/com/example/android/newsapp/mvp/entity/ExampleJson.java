package com.example.android.newsapp.mvp.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kevinsun on 1/22/18.
 */

public class ExampleJson {

    private Glossary glossary;

    public Glossary getGlossary() {
        return glossary;
    }

    public void setGlossary(Glossary glossary) {
        this.glossary = glossary;
    }

    public static class Glossary {

        private String title;

        @SerializedName("GlossDiv")
        private GlossDiv glossDiv;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public GlossDiv getGlossDiv() {
            return glossDiv;
        }

        public void setGlossDiv(GlossDiv glossDiv) {
            this.glossDiv = glossDiv;
        }

        public static class GlossDiv {

            private String title;

            @SerializedName("GlossList")
            private GlossList glossList;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public GlossList getGlossList() {
                return glossList;
            }

            public void setGlossList(GlossList glossList) {
                this.glossList = glossList;
            }


            public static class GlossList {

                @SerializedName("GlossEntry")
                private GlossEntry glossEntry;

                public GlossEntry getGlossEntry() {
                    return glossEntry;
                }

                public void setGlossEntry(GlossEntry glossEntry) {
                    this.glossEntry = glossEntry;
                }


                public static class GlossEntry {
                    private String ID;

                    private String sortAs;

                    private String GlossTerm;

                    private String Acronym;

                    private String Abbrev;

                    private GlossDef glossDef;

                    private String GlossSee;

                    public String getID() {
                        return ID;
                    }

                    public void setID(String ID) {
                        this.ID = ID;
                    }

                    public String getSortAs() {
                        return sortAs;
                    }

                    public void setSortAs(String sortAs) {
                        this.sortAs = sortAs;
                    }

                    public String getGlossTerm() {
                        return GlossTerm;
                    }

                    public void setGlossTerm(String glossTerm) {
                        GlossTerm = glossTerm;
                    }

                    public String getAcronym() {
                        return Acronym;
                    }

                    public void setAcronym(String acronym) {
                        Acronym = acronym;
                    }

                    public String getAbbrev() {
                        return Abbrev;
                    }

                    public void setAbbrev(String abbrev) {
                        Abbrev = abbrev;
                    }

                    public GlossDef getGlossDef() {
                        return glossDef;
                    }

                    public void setGlossDef(GlossDef glossDef) {
                        this.glossDef = glossDef;
                    }

                    public String getGlossSee() {
                        return GlossSee;
                    }

                    public void setGlossSee(String glossSee) {
                        GlossSee = glossSee;
                    }


                    public static class GlossDef {
                        private String para;

                        private List<GlossSeeAlso> glossSeeAlso;

                        public String getPara() {
                            return para;
                        }

                        public void setPara(String para) {
                            this.para = para;
                        }

                        public List<GlossSeeAlso> getGlossSeeAlso() {
                            return glossSeeAlso;
                        }

                        public void setGlossSeeAlso(List<GlossSeeAlso> glossSeeAlso) {
                            this.glossSeeAlso = glossSeeAlso;
                        }

                        public static class GlossSeeAlso {
                            private String name;

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }
                        }

                    }
                }


            }
        }
    }
}
