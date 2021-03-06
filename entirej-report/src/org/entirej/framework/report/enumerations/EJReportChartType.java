package org.entirej.framework.report.enumerations;

public enum EJReportChartType
{
    BAR_CHART, PIE_CHART, STACKED_BAR_CHART, AREA_CHART, STACKED_AREA_CHART, LINE_CHART, XY_AREA_CHART, XY_BAR_CHART, XY_LINE_CHART;

    public String toString()
    {
        switch (this)
        {

            case LINE_CHART:
                return "Line Chart";
            case BAR_CHART:
                return "Bar Chart";
            case AREA_CHART:
                return "Area Chart";
            case STACKED_AREA_CHART:
                return "Stacked Area Chart";
            case STACKED_BAR_CHART:
                return "Stacked Bar Chart";
            case PIE_CHART:
                return "Pie Chart";
            case XY_AREA_CHART:
                return "XY Area Chart";
            case XY_BAR_CHART:
                return "XY Bar Chart";
            case XY_LINE_CHART:
                return "XY Line Chart";

            default:
                return super.toString();
        }
    }

}
