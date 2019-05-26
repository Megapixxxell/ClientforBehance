package com.example.data.repository;

import com.example.data.database.BehanceDao;
import com.example.domain.model.project.Cover;
import com.example.domain.model.project.Owner;
import com.example.domain.model.project.Project;
import com.example.domain.model.project.Stats;
import com.example.domain.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;

public class ProjectDBRepository implements ProjectRepository {

    private List<Cover> mCovers = new ArrayList<>();
    private List<Owner> mOwners = new ArrayList<>();
    private List<Stats> mStats = new ArrayList<>();

    @Inject
    BehanceDao mBehanceDao;

    @Inject
    public ProjectDBRepository() {
    }

    @Override
    public Single<List<Project>> getProjects() {

        return Single.fromCallable(new Callable<List<Project>>() {
            @Override
            public List<Project> call() throws Exception {
                List<Project> projects = mBehanceDao.getProjects();
                for (Project project : projects) {
                    project.setCover(mBehanceDao.getCoverFromProject(project.getId()));
                    project.setOwners(mBehanceDao.getOwnersFromProject(project.getId()));
                }
                return projects;
            }
        });
    }

    @Override
    public void insertProjects(List<Project> projects) {

        getCoversOwnersStats(projects);

        mBehanceDao.insertProjects(projects);

        mBehanceDao.clearCoverTable();
        mBehanceDao.insertCovers(mCovers);

        mBehanceDao.clearOwnerTable();
        mBehanceDao.insertOwners(mOwners);

        mBehanceDao.clearStatsTable();
        mBehanceDao.insertStats(mStats);
    }

    private void getCoversOwnersStats(List<Project> projects) {
        for (int i = 0; i < projects.size(); i++) {

            int projectId = projects.get(i).getId();

            Cover cover = projects.get(i).getCover();
            cover.setId(i);
            cover.setProjectId(projectId);
            mCovers.add(cover);

            Owner owner = projects.get(i).getOwners().get(0);
            owner.setId(i);
            owner.setProjectId(projectId);
            mOwners.add(owner);

            Stats stats = projects.get(i).getStats();
            stats.setId(i);
            stats.setProjectId(projectId);
            mStats.add(stats);
        }
    }

}
