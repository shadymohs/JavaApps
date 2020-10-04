package com.SAPTCO.common.config;

/**
 * @author Shady
 */

public enum ReportInfo {
	
	Header_Vertical("commonHeaderVerticalReport", ""),
	Header_Horizontal("commonHeaderHorizontalReport", ""),
	Shipment("Shipment", "Reports.shipment"),
	ShipmentManual("ShipmentManual", "Reports.shipment"),
	NonRevenue("nonRevenue", "Reports.nonRevenue"),
	TempForm("temperatureForm", "Reports.temperatureForm"),
	PassengerluggageForm("passengerLuggage", "Reports.passengerLuggage"),
	Revenue_Summary("revenueSummary","Reports.revenueSummary"),
	Revenue_SummaryManual("revenueSummaryManual","Reports.revenueSummary"),
	Integration_SummaryManual("financialReport","Reports.IntegrationManual"),
	Shipment_Summary("shipmentSummary","Reports.shipmentSummary"),
	Shipment_SummaryManual("shipmentSummaryManual","Reports.shipmentSummary"),
	Shipment_IncommingManual("shipmentIncommingManual","Reports.shipmentOutgoing"),
	Shipment_OutgoingManual("shipmentOutgoingManual","Reports.shipmentIncoming"),
	Shipment_DriversHistoryManual("driversHistoryManual","Reports.driversHistory"),
	Shipment_TrucksHistoryManual("trucksHistoryManual","Reports.trucksHistory"),
	Passenger_luggage_report("passengerLuggage",""),
	Loading_report("shipment_loading",""),
	Departure_report("shipment_departure",""),
	Unloading_report("shipment_unloading",""),
	Delivery_report("shipment_delivery",""),
	Loading_reportManual("shipment_loading_manual",""),
	Departure_reportManual("shipment_departure_manual",""),
	Unloading_reportManual("shipment_unloading_manual",""),	
	Delivery_reportManual("shipment_delivery_manual",""),
	All_Drivers("allDriversHistoryManual","Reports.allDrivers"),
	All_Trucks("allTrucksHistoryManual","Reports.allTrucks"),
	Shipment_Status("shipmentStatus","Reports.shipmentStatus"),
	Trip_sheet("driverAllowance","driverAllowance"),
	Integ_allowance("integratedAllowanc","integratedAllowanc") ,
	HR_Branch_Driver_Allawance("hrBranchDriverAllawance","hrBranchDriverAllawance"),	
	Trip_daily_detail("TripDailySubReport","tripDetailsReport"),
	Trip_daily_summary("TripSummarySubReport","tripSummaryReport"),	
	Line_daily_detail("LineDailySubReport","LineDetailsReport"),
	Line_daily_summary("LineSummarySubReport","lineSummaryReport"),	
	Revenue_compare_report("RevenueCompareReport","RevenuecompareReport"),	
	Revenue_target_detail("RevenueTargetDailyComparisonReport","LineDetailsReport"),
	Revenue_target_summary("RevenueTargetSummaryComparisonReport","lineSummaryReport"),	
	Passengers_summary_by_line("PassengerSummaryByLineReport","PassengerSummaryByLineReport"),
	Passengers_detail_by_line("PassengerDetailsByLineReport","PassengerDetailsByLineReport"),
	Un_Paid_Sadad("unPaidSadadReport","unpaidSadadTitle"),
	Cancelled_sadad("CancelledSadadReport","cancelledSadadTitle"),
	Paid_Sadad("PaidSadadReport","paidSadadTitle"),	
	complaints_report("complaintsReport","complaintsReportTitle"),
	complaint_history_report("complaintHistoryReport","complaintHistoryReportTitle"),
	luggage_report("luggageReport","luggageReportTitle"),
	luggage_history_report("luggageHistoryReport","luggageHistoryReportTitle"),
	FixedAssets("fixedAssets", "Reports.fixedAssets"),
	FA("FA", "Reports.fixedAssets"),
	Receipts("Receiving", "Reports.receipts"),
	Passenger_Names_report("passengerNamesReport","passengerNamesReport"),	
	jndr_trips_report("jndrTripsReport","jndrTripsReportTitle"),
	jndr_tickets_report("jndrTicketsReport","jndrTicketsReportTitle"),
	jndr_trips_detail_report("jndrTripsDetailReport","jndrTripsDetailReportTitle"),
	jndr_tickets_detail_report("jndrTicketsDetailReport","jndrTicketsDetailReportTitle"),
	Online_Payments_SADAD("onlinePaymentsSadad","onlinePaymentsSadadReport"),
	Online_Payments_Credit("onlinePaymentsCredit","onlinePaymentsCreditReport"),
	Online_Payments_SADAD_Excel("onlinePaymentsSadadExcel","onlinePaymentsSadadReport"),
	Online_Payments_Credit_Excel("onlinePaymentsCreditExcel","onlinePaymentsCreditReport"),
	Online_Payments_TR("onlinePaymentsTR","onlinePaymentsTRReport"),
	Online_Payments_TR_Excel("onlinePaymentsTRExcel","onlinePaymentsTRReport"),
	Haj_Limo_ticket("hajLimoTicket","onlinePaymentsTRReport"),
	Haj_Limo_Details_report("hajLimodetails","onlinePaymentsTRReport"),
	Haj_Limo_Summary_report("hajLimoSummary","onlinePaymentsTRReport"),
	Haj_Limo_Details_sub_report("hajLimoDetails_subreport","onlinePaymentsTRReport"),
	Haj_Limo_Cancelled_report("hajLimoCanncelled","onlinePaymentsTRReport"),
	KAUST_Allowance_report("kaustIntegrated","onlinePaymentsTRReport")
	;
	
	private final String reportName;
	private final String reportTitle;
	
	private ReportInfo(String reportName, String reportTitle) {
		this.reportName = reportName;
		this.reportTitle = reportTitle;
	}
	public String getReportName() {
		return reportName;
	}
	public String getReportTitle() {
		return reportTitle;
	}

}
