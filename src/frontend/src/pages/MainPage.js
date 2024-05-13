import React, {useState, useEffect} from 'react';
import Team from '../components/Team';
import '../styles/MainPage.scss';

function MainPage(props) {

    useEffect(() => {

        const fetchTeams = async ()=> {
            const response = await fetch(`http://localhost:8080/team`);
            const data = await response.json();

            console.log(data);
            setTeams(data);

        }

        fetchTeams();
    }, []);


    const [teams, setTeams] = useState([]);
    return (
        <div className="MainPage">
            <div className="header">
                <h1>NBA STATS VISUALIZER</h1>
            </div>

            <div className="team-grid">
                {teams && teams.map(team=><Team teamName={team.teamName}/>)}
            </div>
        </div>
    );
}

export default MainPage;