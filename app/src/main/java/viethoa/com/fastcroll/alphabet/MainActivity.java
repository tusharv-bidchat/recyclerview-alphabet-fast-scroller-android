package viethoa.com.fastcroll.alphabet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.viethoa.RecyclerViewFastScroller;
import com.viethoa.models.AlphabetItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.fast_scroller)
    RecyclerViewFastScroller fastScroller;

    private List<String> mDataArray;
    private List<AlphabetItem> mAlphabetItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initialiseData();
        initialiseUI();
    }

    protected void initialiseData() {
        //Recycler view data
        mDataArray = DataHelper.getAlphabetData();

        List<String> uniqueDataList = new ArrayList<>();
        for (int i = 0; i < mDataArray.size(); i++) {
            String name = mDataArray.get(i);
            if (name == null || name.trim().isEmpty())
                continue;

            if (!uniqueDataList.contains(name)) {
                uniqueDataList.add(name);
            }
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 1);
        Date startDate = calendar.getTime();
        calendar.set(Calendar.MONTH, 3);
        Date endDate = calendar.getTime();
        initialiseSectionIndexes(uniqueDataList, startDate, endDate);
    }

    protected void initialiseUI() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerViewAdapter(mDataArray));

        fastScroller.setRecyclerView(mRecyclerView);
        fastScroller.setUpAlphabet(mAlphabetItems);
    }

    protected void initialiseSectionIndexes(List<String> uniqueDataList, Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        mAlphabetItems = new ArrayList<>();
        int position;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String key;

        while (calendar.getTime().before(endDate))
        {
            position = -1;
            Date result = calendar.getTime();
            if (uniqueDataList.contains(dateFormat.format(result))) {
                position = uniqueDataList.indexOf(dateFormat.format(result));
            }
            key = dateFormat.format(result).substring(0, dateFormat.format(result).indexOf("-"));
            if (mAlphabetItems.isEmpty()) {
                mAlphabetItems.add(new AlphabetItem(position, key, dateFormat.format(result), true));
            } else {
                mAlphabetItems.add(new AlphabetItem(position, key, dateFormat.format(result), false));
            }
            calendar.add(Calendar.DATE, 1);
        }
    }
}
