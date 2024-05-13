package com.batch.nbadashboardjava.controller;


import com.batch.nbadashboardjava.model.Matches;
import com.batch.nbadashboardjava.model.Teams;
import com.batch.nbadashboardjava.repository.MatchRepository;
import com.batch.nbadashboardjava.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;


    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team/{teamName}")
    public Teams getTeam(@PathVariable String teamName){
        Teams team = this.teamRepository.findByTeamName(teamName);
        Pageable pageable = PageRequest.of(0, 4);
        team.setMatches(this.matchRepository.getByTeam1OrTeam2OrderByDateDesc(teamName, pageable));
        return team;
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Matches> getGamesForTeam(@PathVariable String teamName, @RequestParam String year){

        return this.matchRepository.findByTeamNameAndSeason(teamName, year);
    }

    @GetMapping("/team")
    public Iterable<Teams> getTeams(){
        return this.teamRepository.findAllTeams();
    }
}
