/**
 * Copyright (C) 2015 The AppFramework Project
 */
package com.hyena.framework.app.widget;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * RecycleView Wrapper
 * @author yangzc
 */
public class SimpleRecycleView extends RecyclerView {

//	private View mEmptyView;
	
	public SimpleRecycleView(Context context, AttributeSet attrs, int arg2) {
		super(context, attrs, arg2);
	}

	public SimpleRecycleView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SimpleRecycleView(Context context) {
		super(context);
	}

	@Override
	public void setLayoutManager(LayoutManager layout) {
		super.setLayoutManager(layout);
	}

	@Override
	public void setItemAnimator(ItemAnimator animator) {
		super.setItemAnimator(animator);
	}
	
//	public void setEmptyView(View empty){
//		this.mEmptyView = empty;
//	}
	
	/**
	 * BaseListItem
	 */
	public static class BaseListItem implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 8031996099782857208L;
		//type of listitem
		public int mType = 0;
	}

	@SuppressWarnings("rawtypes")
	public static class SingleViewAdapter extends SingleTypeAdapter {

		private View mView;
		private int mType = 0;

		public SingleViewAdapter(Context context, View view){
			super(context);
			this.mView = view;
		}

		public SingleViewAdapter(Context context, View view, int type){
			super(context);
			this.mView = view;
			this.mType = type;
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
			return new SingleViewHolder(mView);
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int i) {
		}

		@Override
		public int getItemCount() {
			return 1;
		}

		@Override
		public int getItemViewType(int position) {
			return mType;
		}

		public class SingleViewHolder extends ViewHolder {

			public SingleViewHolder(View itemView) {
				super(itemView);
			}
		}
	}

	/**
	 * SingleType RecycleView Adapter
	 * @param <T>
	 */
	@SuppressWarnings("rawtypes")
	public abstract static class SingleTypeAdapter<T extends BaseListItem> extends RecyclerView.Adapter {

		private List<T> mItems;
		protected Context mContext;

		public SingleTypeAdapter(Context context) {
			super();
			this.mContext = context;
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
			return null;
		}

		@Override
		public void onBindViewHolder(ViewHolder viewHolder, int i) {

		}

		@Override
		public int getItemCount() {
			if(mItems == null)
				return 0;
			return mItems.size();
		}

		@Override
		public int getItemViewType(int position) {
			T item = getItem(position);
			if (item != null)
				return item.mType;
			return super.getItemViewType(position);
		}

		public T getItem(int position) {
			if(mItems == null)
				return null;
			if(position < mItems.size())
				return mItems.get(position);
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		public void setItems(List<T> items){
			this.mItems = items;
			notifyDataSetChanged();
		}

		public void addItems(List<T> items) {
			if(mItems != null) {
				this.mItems.addAll(items);
				notifyDataSetChanged();
			}
		}

		public List<T> getItems(){
			return mItems;
		}

		public void removeItem(T t){
			int index = mItems.indexOf(t);
			if(index >= 0) {
				mItems.remove(t);
				notifyItemRemoved(index);
			}
		}

		public void addItem(T t){
			if(!mItems.contains(t)) {
				mItems.add(t);
				notifyItemInserted(getItemCount() - 1);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static class MultiTypeAdapter extends RecyclerView.Adapter {

		//适配器列表
		private List<SingleTypeAdapter> mAdapters = new ArrayList<SingleTypeAdapter>();
		//上下文
		Context mContext;

		public MultiTypeAdapter(Context context){
			this.mContext = context;
		}

		/**
		 * 添加适配器
		 * @param adapter
		 */
		public void addAdapter(final SingleTypeAdapter adapter) {
			mAdapters.add(adapter);
			adapter.registerAdapterDataObserver(mAdapterObserver);
		}

		/**
		 * 清空数据
		 */
		public void removeAllAdapters() {
			if (mAdapters != null) {
				if (mAdapters != null && !mAdapters.isEmpty()) {
					for (int i = 0; i < mAdapters.size(); i++) {
						mAdapters.get(i).unregisterAdapterDataObserver(mAdapterObserver);
					}
				}
				mAdapters.clear();
				notifyDataSetChanged();
			}
		}

		/**
		 * 设置适配器
		 * @param adapters
		 */
		public void setAdapters(List<SingleTypeAdapter> adapters){
			this.mAdapters = adapters;
			if (mAdapters != null && !mAdapters.isEmpty()) {
				for (int i = 0; i < mAdapters.size(); i++) {
					mAdapters.get(i).unregisterAdapterDataObserver(mAdapterObserver);
					mAdapters.get(i).registerAdapterDataObserver(mAdapterObserver);
				}
			}
			notifyDataSetChanged();
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
			if (mAdapters == null)
				return null;

			int p = position;

			for (int i = 0; i < mAdapters.size(); i++) {
				SingleTypeAdapter adapter = mAdapters.get(i);
				int cnt = adapter.getItemCount();
				if (p < cnt) {
					return adapter.onCreateViewHolder(viewGroup, p);
				}
				p -= cnt;
			}
			return null;
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {
			if (mAdapters == null)
				return;
			int p = position;

			for (int i = 0; i < mAdapters.size(); i++) {
				SingleTypeAdapter adapter = mAdapters.get(i);
				int cnt = adapter.getItemCount();
				if (p < cnt) {
					adapter.onBindViewHolder(holder, p);
					return;
				}
				p -= cnt;
			}
		}

		@Override
		public int getItemCount() {
			if (mAdapters == null)
				return 0;

			int cnt = 0;
			for (int i = 0; i < mAdapters.size(); i++) {
				SingleTypeAdapter adapter = mAdapters.get(i);
				cnt += adapter.getItemCount();
			}
			return cnt;
		}

		@Override
		public int getItemViewType(int position) {
			if (mAdapters == null) {
				return super.getItemViewType(position);
			}
			int p = position;
			for (int i = 0; i < mAdapters.size(); i++) {
				SingleTypeAdapter adapter = mAdapters.get(i);
				int cnt = adapter.getItemCount();
				if (p < cnt) {
					return adapter.getItemViewType(p);
				}
				p -= cnt;
			}
			return super.getItemViewType(position);
		}

		@Override
		public void setHasStableIds(boolean hasStableIds) {
			super.setHasStableIds(hasStableIds);
			if (mAdapters == null)
				return;
			for (int i = 0; i < mAdapters.size(); i++) {
				SingleTypeAdapter adapter = mAdapters.get(i);
				adapter.setHasStableIds(hasStableIds);
			}
		}

		@Override
		public long getItemId(int position) {
			if (mAdapters == null)
				return 0;
			int p = position;

			for (int i = 0; i < mAdapters.size(); i++) {
				SingleTypeAdapter adapter = mAdapters.get(i);
				int cnt = adapter.getItemCount();
				if (p < cnt) {
					return adapter.getItemId(p);
				}
				p -= cnt;
			}
			return 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void onViewRecycled(ViewHolder holder) {
			super.onViewRecycled(holder);
			if (mAdapters == null)
				return;

			for (int i = 0; i < mAdapters.size(); i++) {
				SingleTypeAdapter adapter = mAdapters.get(i);
				adapter.onViewRecycled(holder);
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public void onViewAttachedToWindow(ViewHolder holder) {
			super.onViewAttachedToWindow(holder);
			if (mAdapters == null)
				return;

			for (int i = 0; i < mAdapters.size(); i++) {
				SingleTypeAdapter adapter = mAdapters.get(i);
				adapter.onViewAttachedToWindow(holder);
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public void onViewDetachedFromWindow(ViewHolder holder) {
			super.onViewDetachedFromWindow(holder);
			if (mAdapters == null)
				return;

			for (int i = 0; i < mAdapters.size(); i++) {
				SingleTypeAdapter adapter = mAdapters.get(i);
				adapter.onViewDetachedFromWindow(holder);
			}
		}

		@Override
		public void registerAdapterDataObserver(AdapterDataObserver observer) {
			super.registerAdapterDataObserver(observer);
		}

		@Override
		public void unregisterAdapterDataObserver(AdapterDataObserver observer) {
			super.unregisterAdapterDataObserver(observer);
		}

		//TODO 存在bug,有时间修复，慎用...
		//数据集观察者
		private AdapterDataObserver mAdapterObserver = new AdapterDataObserver(){

			@Override
			public void onChanged() {
				super.onChanged();
				MultiTypeAdapter.this.notifyDataSetChanged();
			}

			@Override
			public void onItemRangeChanged(int positionStart, int itemCount) {
				super.onItemRangeChanged(positionStart, itemCount);
				MultiTypeAdapter.this.notifyItemRangeChanged(positionStart, itemCount);
			}

			@Override
			public void onItemRangeInserted(int positionStart, int itemCount) {
				super.onItemRangeInserted(positionStart, itemCount);
				MultiTypeAdapter.this.notifyItemRangeInserted(positionStart, itemCount);
			}

			@Override
			public void onItemRangeRemoved(int positionStart, int itemCount) {
				super.onItemRangeRemoved(positionStart, itemCount);
				MultiTypeAdapter.this.notifyItemRangeRemoved(positionStart, itemCount);
			}

			@Override
			public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
				super.onItemRangeMoved(fromPosition, toPosition, itemCount);
				MultiTypeAdapter.this.notifyItemMoved(fromPosition, toPosition);
			}

		};
	}

	/**
	 * Simple ViewHolder of RecycleView
	 */
	public static abstract class SimpleViewHolder extends ViewHolder {

		public SimpleViewHolder(View itemView) {
			super(itemView);
		}

	}
	
}
