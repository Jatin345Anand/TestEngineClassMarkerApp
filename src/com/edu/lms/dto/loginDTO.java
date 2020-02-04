package com.edu.lms.dto;

public class loginDTO {
		private int Loginid;
		private int roleid;
		private String Username;
		private String password;
		private String Email;
		public int getLoginid() {
			return Loginid;
		}
		public void setLoginid(int loginid) {
			Loginid = loginid;
		}
		public int getRoleid() {
			return roleid;
		}
		public void setRoleid(int roleid) {
			this.roleid = roleid;
		}
		public String getUsername() {
			return Username;
		}
		public void setUsername(String username) {
			Username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return Email;
		}
		public void setEmail(String email) {
			Email = email;
		}
}