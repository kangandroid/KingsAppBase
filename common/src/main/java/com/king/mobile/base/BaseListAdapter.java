package com.king.mobile.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.king.mobile.util.BindLayoutMapping;
import com.king.mobile.util.InstanceUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseListAdapter<T> extends
        RecyclerView.Adapter<BaseListAdapter.BaseViewHolder<T>> {
    private final String TAG = getClass().getSimpleName();
    private List<T> mList = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Class<?>> mViewBundles;
    private OnItemClickListener<T> mOnItemClickLitener;
    private OnItemLongClickListener<T> mOnItemLongClickLitener;

    public void setData(List<T> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T t, View view, int position);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(T t, View view, int position);
    }

    public BaseListAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mViewBundles = new ArrayList<>();
        onBindVHLayoutId(mViewBundles);
    }

    public void setOnItemClickLitener(OnItemClickListener<T> onItemClickLitener) {
        this.mOnItemClickLitener = onItemClickLitener;
    }


    @Override
    public BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mViewBundles != null && mViewBundles.size() > 0) {
            Class viewBundle = findViewHolderClazz(viewType);
            int layoutId = BindLayoutMapping.getLayoutId(viewBundle);
            View view = mInflater.inflate(layoutId, parent, false);
            return (BaseViewHolder<T>) InstanceUtil.getInstance(viewBundle, new Class[]{View.class}, new Object[]{view});

        } else {
            throw new IllegalArgumentException("view bundles can not be null or empty!");
        }
    }

    private Class findViewHolderClazz(int viewType) {
        return mViewBundles.get(viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T> holder, final int position) {
        try {
            holder.bindView(mList.get(position), position, mContext);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClickLitener != null) {
                mOnItemClickLitener.onItemClick(mList.get(position), holder.itemView, holder.getLayoutPosition());
            }
        });
        holder.itemView.setOnLongClickListener(v -> {
            if (mOnItemLongClickLitener != null) {
                mOnItemLongClickLitener.onItemLongClick(mList.get(position), holder.itemView,
                        holder.getLayoutPosition());
                return true;
            } else {
                return false;
            }
        });
    }

    /**
     * 绑定 vh和layoutid
     * <p>
     * 该list的index表示对应的view type
     */
    protected abstract void onBindVHLayoutId(List<Class<?>> VhClazzList);

    /**
     * 如果viewbundles size=1 不需要重写该函数，对于包含多种条目的List 位置与条目类型的关系
     *
     * @param position 位置
     * @return 0-n
     */
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public T getItemBean(int position) {
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addList(List<T> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void removeItemAt(int position) {
        if (position < 0 || position >= mList.size()) return;
        mList.remove(position);
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return mList;
    }

    public void addItem(T item) {
        mList.add(item);
        notifyDataSetChanged();
    }

    public void addItemAtBegin(T item) {
        mList.add(0, item);
        notifyDataSetChanged();
    }

    public void initList(List<T> list) {
        if (mList.size() != 0)
            mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearList() {
        mList.clear();
    }

    public static abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

        public Context getContext() {
            return itemView.getContext();
        }

        public BaseViewHolder(View itemView) {
            super(itemView);
        }


        /**
         * 设置该viewHolder上数据
         *
         * @param bean     数据对象
         * @param position 所在位置
         * @param context  上下文
         */
        protected abstract void bindView(T bean, int position, Context context) throws ParseException;

    }

    /**
     * view LayouotId 与 holder 关系
     */
    public static class ViewBundle {
        public ViewBundle(int layoutId, Class<? extends BaseViewHolder> clazz) {
            this.layoutId = layoutId;
            this.VHclazz = clazz;
        }

        public int layoutId;
        public Class VHclazz;
    }
}
