package place;



import com.google.gson.Gson;

public class PlaceGroupList {
	private Place[] Items;

    private String PageSize;

    private String Page;

    private String TotalResults;

    private String HasMoreResults;

    public Place getItemN (int i)
    {
        return Items[i];
    }

    public void setItems (Place[] Items)
    {
        this.Items = Items;
    }

    public String getPageSize ()
    {
        return PageSize;
    }

    public void setPageSize (String PageSize)
    {
        this.PageSize = PageSize;
    }

    public String getPage ()
    {
        return Page;
    }

    public void setPage (String Page)
    {
        this.Page = Page;
    }

    public int getTotalResults ()
    {
        return Integer.parseInt(TotalResults);
    }

    public void setTotalResults (String TotalResults)
    {
        this.TotalResults = TotalResults;
    }

    public String getHasMoreResults ()
    {
        return HasMoreResults;
    }

    public void setHasMoreResults (String HasMoreResults)
    {
        this.HasMoreResults = HasMoreResults;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Items = "+Items+", PageSize = "+PageSize+", Page = "+Page+", TotalResults = "+TotalResults+", HasMoreResults = "+HasMoreResults+"]";
    }
    public static PlaceGroupList Json2Obj(String jsondata){
		Gson gson = new Gson();
		
		PlaceGroupList obj = gson.fromJson(jsondata, PlaceGroupList.class);
		
		return obj;
	}
}
