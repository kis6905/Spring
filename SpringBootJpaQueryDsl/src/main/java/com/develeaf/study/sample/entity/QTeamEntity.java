package com.develeaf.study.sample.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTeamEntity is a Querydsl query type for TeamEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTeamEntity extends EntityPathBase<TeamEntity> {

    private static final long serialVersionUID = 1798562079L;

    public static final QTeamEntity teamEntity = new QTeamEntity("teamEntity");

    public final StringPath name = createString("name");

    public final NumberPath<Long> teamSeq = createNumber("teamSeq", Long.class);

    public QTeamEntity(String variable) {
        super(TeamEntity.class, forVariable(variable));
    }

    public QTeamEntity(Path<? extends TeamEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeamEntity(PathMetadata metadata) {
        super(TeamEntity.class, metadata);
    }

}

