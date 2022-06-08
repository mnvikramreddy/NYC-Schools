package com.example.nycschools.network.model

import com.google.gson.annotations.SerializedName

data class SchoolsDirectoryResponse(

    @SerializedName("dbn")
    val dbn: String? = null,
    @SerializedName("school_name")
    val schoolName: String? = null,
    @SerializedName("boro")
    val boro: String? = null,
    @SerializedName("overview_paragraph")
    val overviewParagraph: String? = null,
    @SerializedName("school_10th_seats")
    val school10thSeats: String? = null,
    @SerializedName("academicopportunities1")
    val academicopportunities1: String? = null,
    @SerializedName("academicopportunities2")
    val academicopportunities2: String? = null,
    @SerializedName("academicopportunities3")
    val academicopportunities3: String? = null,
    @SerializedName("ell_programs")
    val ellPrograms: String? = null,
    @SerializedName("language_classes")
    val languageClasses: String? = null,
    @SerializedName("advancedplacement_courses")
    val advancedplacementCourses: String? = null,
    @SerializedName("neighborhood")
    val neighborhood: String? = null,
    @SerializedName("shared_space")
    val sharedSpace: String? = null,
    @SerializedName("building_code")
    val buildingCode: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("phone_number")
    val phoneNumber: String? = null,
    @SerializedName("fax_number")
    val faxNumber: String? = null,
    @SerializedName("school_email")
    val schoolEmail: String? = null,
    @SerializedName("website")
    val website: String? = null,
    @SerializedName("subway")
    val subway: String? = null,
    @SerializedName("bus")
    val bus: String? = null,
    @SerializedName("grades2018")
    val grades2018: String? = null,
    @SerializedName("finalgrades")
    val finalgrades: String? = null,
    @SerializedName("total_students")
    val totalStudents: String? = null,
    @SerializedName("start_time")
    val startTime: String? = null,
    @SerializedName("end_time")
    val endTime: String? = null,
    @SerializedName("extracurricular_activities")
    val extracurricularActivities: String? = null,
    @SerializedName("psal_sports_boys")
    val psalSportsBoys: String? = null,
    @SerializedName("psal_sports_girls")
    val psalSportsGirls: String? = null,
    @SerializedName("psal_sports_coed")
    val psalSportsCoed: String? = null,
    @SerializedName("school_sports")
    val schoolSports: String? = null,
    @SerializedName("graduation_rate")
    val graduationRate: String? = null,
    @SerializedName("attendance_rate")
    val attendanceRate: String? = null,
    @SerializedName("pct_stu_enough_valiety")
    val pctStuEnoughvaliety: String? = null,
    @SerializedName("college_career_rate")
    val collegeCareerRate: String? = null,
    @SerializedName("pct_stu_safe")
    val pctStuSafe: String? = null,
    @SerializedName("school_accessibility_description")
    val schoolAccessibilityDescription: String? = null,
    @SerializedName("prgdesc1")
    val prgdesc1: String? = null,
    @SerializedName("offer_rate1")
    val offerRate1: String? = null,
    @SerializedName("program1")
    val program1: String? = null,
    @SerializedName("code1")
    val code1: String? = null,
    @SerializedName("interest1")
    val interest1: String? = null,
    @SerializedName("method1")
    val method1: String? = null,
    @SerializedName("seats9ge1")
    val seats9ge1: String? = null,
    @SerializedName("grade9gefilledflag1")
    val grade9gefilledflag1: String? = null,
    @SerializedName("grade9geapplicants1")
    val grade9geapplicants1: String? = null,
    @SerializedName("seats9swd1")
    val seats9swd1: String? = null,
    @SerializedName("grade9swdfilledflag1")
    val grade9swdfilledflag1: String? = null,
    @SerializedName("grade9swdapplicants1")
    val grade9swdapplicants1: String? = null,
    @SerializedName("seats101")
    val seats101: String? = null,
    @SerializedName("admissionspriority11")
    val admissionspriority11: String? = null,
    @SerializedName("admissionspriority21")
    val admissionspriority21: String? = null,
    @SerializedName("admissionspriority31")
    val admissionspriority31: String? = null,
    @SerializedName("admissionspriority41")
    val admissionspriority41: String? = null,
    @SerializedName("grade9geapplicantsperseat1")
    val grade9geapplicantsperseat1: String? = null,
    @SerializedName("grade9swdapplicantsperseat1")
    val grade9swdapplicantsperseat1: String? = null,
    @SerializedName("primary_address_line_1")
    val primaryAddressLine1: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("zip")
    val zip: String? = null,
    @SerializedName("state_code")
    val stateCode: String? = null,
    @SerializedName("latitude")
    val latitude: String? = null,
    @SerializedName("longitude")
    val longitude: String? = null,
    @SerializedName("community_board")
    val communityBoard: String? = null,
    @SerializedName("council_district")
    val councilDistrict: String? = null,
    @SerializedName("census_tract")
    val censusTract: String? = null,
    @SerializedName("bin")
    val bin: String? = null,
    @SerializedName("bbl")
    val bbl: String? = null,
    @SerializedName("nta")
    val nta: String? = null,
    @SerializedName("borough")
    val borough: String? = null
)
