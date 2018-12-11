package com.xwtec.androidframe.ui.affirmOrder.provider;

import android.widget.EditText;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.xwtec.androidframe.R;
import com.xwtec.androidframe.ui.MultiEntity;
import com.xwtec.androidframe.ui.affirmOrder.AffirmAdapter;

/**
 * @Author ayy
 * @Date 2018/10/22.
 * Describe:xxx
 */

@ItemProviderTag(viewType = AffirmAdapter.DISCOUNT_TYPE,layout = R.layout.discount_code_item)
public class DiscountCodeProvider extends BaseItemProvider<MultiEntity,BaseViewHolder>{
    private  EditText etDiscountCode;

    @Override
    public void convert(BaseViewHolder helper, MultiEntity data, int position) {
        etDiscountCode = helper.getView(R.id.et_discount_code);

    }

    @Override
    public void onClick(BaseViewHolder helper, MultiEntity data, int position) {

    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, MultiEntity data, int position) {
        return false;
    }

    public String getDiscountCode(){
        return etDiscountCode.getText().toString().trim();
    }
}
