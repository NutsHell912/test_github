package com.test.nutshell.search;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.test.nutshell.R;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;


class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    private  SearchMVP.Presenter presenter;
    private  RequestManager glide;


    SearchAdapter(@NotNull SearchMVP.Presenter<SearchMVP.View, SearchMVP.Interactor> presenter,
                         @NotNull RequestManager glide) {
        super();
        this.presenter = presenter;
        this.glide = glide;
        this.setHasStableIds(true);
    }

    public long getItemId(int position) {
        return (long)this.presenter.getRepoId(position);
    }

    public int getItemCount() {
        return this.presenter.getRepoCount();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.repo_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        presenter.bindItemView(holder, position);
    }

    final class ViewHolder extends RecyclerView.ViewHolder implements SearchMVP.ItemView {
        public void renderFullName(@Nullable String fullName) {
            ((TextView)this.itemView.findViewById(R.id.repoName)).setText(fullName);
        }

        public void renderAvatart(@Nullable String photoUrl) {
            Intrinsics.checkParameterIsNotNull(photoUrl, "photoUrl");
            SearchAdapter.this.glide
                    .load(photoUrl)
                    .into((ImageView)this.itemView.findViewById(R.id.avatarImage));
        }

        @Override
        public void renderDescription(@Nullable String description) {
            ((TextView)this.itemView.findViewById(R.id.repoDescription)).setText(description);
        }

        ViewHolder(@NotNull View itemView) {
            super(itemView);
        }
    }

}
