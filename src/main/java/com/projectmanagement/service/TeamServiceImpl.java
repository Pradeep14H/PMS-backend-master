package com.projectmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanagement.entity.Team;
import com.projectmanagement.exceptions.ResourceNotFoundException;
import com.projectmanagement.repo.TeamRepo;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepo teamRepo;

	@Override
	public Team addTeam(Team team) throws Exception {
//		if (teamRepo.findById(team.getTeamId()) != null) {
//			throw new Exception("Team already exists with id : " + team.getTeamId());
//		}

		return teamRepo.save(team);
	}

	@Override
	public Team updateTeam(Team team) throws ResourceNotFoundException {
		Team t = teamRepo.findById(team.getTeamId())
				.orElseThrow(() -> new ResourceNotFoundException("Team doesn't exists"));
		teamRepo.delete(t);
		return teamRepo.save(team);
	}

	@Override
	public Team getTeamById(long teamId) throws ResourceNotFoundException {

		return teamRepo.findById(teamId)
				.orElseThrow(() -> new ResourceNotFoundException("Team doesn't exists with id : " + teamId));
	}

	@Override
	public List<Team> getListOfTeam() {

		return teamRepo.findAll();
	}

	@Override
	public String deleteTeam(long teamId) throws ResourceNotFoundException {
		Team t = teamRepo.findById(teamId)
				.orElseThrow(() -> new ResourceNotFoundException("Team doesn't exists with id : " + teamId));
		teamRepo.delete(t);

		return "Team Doesn't exists with id : " + teamId;
	}

}
