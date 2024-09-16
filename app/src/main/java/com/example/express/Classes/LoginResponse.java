package com.example.express.Classes;

public class LoginResponse {
    private boolean status;
    private String action;
    private Data data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String uuid;
        private String first_name;
        private String last_name;
        private String type;
        private String username;
        private String email;
        private String image;
        private String account_type;
        private String position;
        private int request_verify;
        private int verify;
        private boolean interest_add;
        private String token;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAccount_type() {
            return account_type;
        }

        public void setAccount_type(String account_type) {
            this.account_type = account_type;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getRequest_verify() {
            return request_verify;
        }

        public void setRequest_verify(int request_verify) {
            this.request_verify = request_verify;
        }

        public int getVerify() {
            return verify;
        }

        public void setVerify(int verify) {
            this.verify = verify;
        }

        public boolean isInterest_add() {
            return interest_add;
        }

        public void setInterest_add(boolean interest_add) {
            this.interest_add = interest_add;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }




    }
}
