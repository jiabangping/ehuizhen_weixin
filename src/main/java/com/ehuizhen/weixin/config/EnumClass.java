package com.ehuizhen.weixin.config;

public class EnumClass {

	/**
	 * 课程分类枚举
	 * 
	 * @author Hexy
	 *
	 */
	public enum CourseTypes {
		/**
		 * 销售代表
		 */
		SALES_DEPUTY(0, "销售代表"),
		/**
		 * 销售管理
		 */
		SALES_MANAGER(1, "销售管理"),
		/**
		 * 市场营销
		 */
		MARKETING(2, "市场营销"),
		/**
		 * 人力资源
		 */
		HUMAN_RESOURCES(3, "人力资源");

		private int code;
		private String description;

		/**
		 * 构造函数，枚举类型只能为私有
		 * 
		 * @param code
		 * @param description
		 */
		private CourseTypes(int code, String description) {
			this.code = code;
			this.description = description;
		}

		@Override
		public String toString() {
			return this.description;
		}

		public int getCode() {
			return this.code;
		}
	}
	
	/**
	 * 课程分类枚举
	 * 
	 * @author Hexy
	 *
	 */
	public enum CourseAssistTypes {
		/**
		 * 链接
		 */
		URL(0, "链接"),
		/**
		 * 文件
		 */
		FILE(1, "文件");

		private int code;
		private String description;

		/**
		 * 构造函数，枚举类型只能为私有
		 * 
		 * @param code
		 * @param description
		 */
		private CourseAssistTypes(int code, String description) {
			this.code = code;
			this.description = description;
		}

		@Override
		public String toString() {
			return this.description;
		}

		public int getCode() {
			return this.code;
		}
	}
	
	public enum UserLevel {
		/**
		 * 普通会员
		 */
		DEFAULT('0', "普通会员"),
		/**
		 * VIP会员
		 */
		VIP('1', "VIP会员");

		private char code;
		private String description;

		/**
		 * 构造函数，枚举类型只能为私有
		 * 
		 * @param code
		 * @param description
		 */
		private UserLevel(char code, String description) {
			this.code = code;
			this.description = description;
		}

		@Override
		public String toString() {
			return this.description;
		}

		public char getCode() {
			return this.code;
		}
	}

	/**
	 * 订单状态枚举
	 * 
	 * @author Hexy
	 *
	 */
	public enum OrderStatus {
		/**
		 * 系统作废
		 */
		ORDER_SYSINVALID(-1, "系统作废"),

		/**
		 * 待支付
		 */
		ORDER_PAYING(1, "待支付"),
		/**
		 * 已支付
		 */
		ORDER_PAYED(2, "已支付");

		private int value;
		private String desc;

		private OrderStatus(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}
	}
	
	
	/// <summary>
		/// 图片类型
		/// </summary>
		public enum ImageType {
			/**
			 * 医生头像
			 */
			DOCTORHEADER(0, "DoctorHeader"),
			/**
			 * 广告图片
			 */
			BANNER(1, "Banner"),
			/**
			 * 影像报告
			 */
			MOIVES(2, "Moives"),
			/**
			 * 化验报告
			 */
			ANALYSISREPORT(3, "AnalysisReport"),
			/**
			 * 化验报告
			 */
			LABORATORYSHEET(4, "LaboratorySheet"),
			/**
			 * 病理报告
			 */
			CASEREPORT(5, "CaseReport"),
			/**
			 * 患者头像
			 */
			PATIENTHEADER(6, "PatientHeader"),
			/**
			 * 科室图片
			 */
			DEPARTMENTPICTURE(7, "DepartmentPicture"),
			/**
			 * 诊断结果
			 */
			CASERESULT(8, "CaseResult"),
			/**
			 * 病历
			 */
			CASE(9, "Case"),
			/**
			 * 影像资料
			 */
			PICTURE(10, "Picture"),
			/**
			 * 医嘱资料
			 */
			REMITTANCE(11, "Remittance"),

			/**
			 * 医院Logo
			 */
			HOSPITALPICUTRE(12, "HospitalPicutre");

			private int code;
			private String description;

			/**
			 * 构造函数，枚举类型只能为私有
			 * 
			 * @param code
			 * @param description
			 */
			private ImageType(int code, String description) {
				this.code = code;
				this.description = description;
			}

			@Override
			public String toString() {
				return this.description;
			}

			public int getCode() {
				return this.code;
			}
		}

}
