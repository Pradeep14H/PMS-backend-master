package com.projectmanagement.service;

import java.util.List;

import com.projectmanagement.entity.Team;
import com.projectmanagement.exceptions.ResourceNotFoundException;

public interface TeamService {

	Team addTeam(Team team) throws Exception;

	Team updateTeam(Team team) throws ResourceNotFoundException;

	Team getTeamById(long teamId) throws ResourceNotFoundException;

	List<Team> getListOfTeam();

	String deleteTeam(long teamId) throws ResourceNotFoundException;

}
