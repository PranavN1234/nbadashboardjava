import React, { useEffect, useState } from 'react';
import {useParams} from "react-router-dom";
import Information from "../components/Information";
import GameCard from "../components/GameCard";
import '../styles/Home.scss';
import { PieChart } from 'react-minimal-pie-chart';
import {Link} from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHome } from '@fortawesome/free-solid-svg-icons';
export const Home =()=> {

    const [team, setTeam] = useState();
    const {teamName} = useParams();
    useEffect(() => {
        const fetchMatches = async ()=>{
            const response = await fetch(`http://localhost:8080/team/${teamName}`);
            const data = await response.json();

            console.log(data);
            setTeam(data);
        };

        fetchMatches();
    }, [teamName]);

    if(!team || !team.teamName){
        return <h1>Team Not Found</h1>
    }
    
    return (
        <div className="Home">

            <div className="team-name">
                <h1 className="name-section">{team && team.teamName}</h1>
                <Link to="/" className="home-button"><FontAwesomeIcon icon={faHome} /></Link>
            </div>

            <div className="team-winning">
                Wins/Losses
                <PieChart
                    data={[
                        { title: 'Wins', value: team.totalWins, color: '#9CCC65' },
                        { title: 'Losses', value: team.totalMatches - team.totalWins, color: '#EF5350' },
                    ]}
                    style={{ width: '200px', height: '200px' }} // Set your desired width and height here
                />
            </div>
            <div className="game-info">
                <h3>Matches</h3>
                {team && <Information teamName={team.teamName} game={team.matches[0]}/>}
            </div>

            {
                team && team.matches.slice(1).map(game=><GameCard teamName={team.teamName} game={game}/>)
            }

            <div className="more-link">
                <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More</Link>
            </div>

        </div>
    );
}

export default Home;

