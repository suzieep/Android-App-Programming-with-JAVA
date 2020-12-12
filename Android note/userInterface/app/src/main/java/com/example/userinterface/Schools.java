package com.example.userinterface;




    public class Schools {
        // 학교 이름
        String school_name;
        // 이미지 아이디
        int image_id;
        // 학교 웹 주소
        String school_url;

        public Schools(int id, String name, String url) {
            this.image_id = id;
            this.school_name = name;
            this.school_url = url;
        }

        public String getSchoolName() {
            return school_name;
        }

        public int getImageID() {
            return image_id;
        }

        public String getURL() {
            return school_url;
        }
    }

