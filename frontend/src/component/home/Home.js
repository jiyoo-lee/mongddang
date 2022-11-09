import React from "react";
import HomeContents from "./HomeContents";
import "./Home.css";



function Home () {

    return (
    <>
        <div className="background">
            <img src="img/background_home.png" alt="background" className="backgroundimg"/>
        </div>
        <div className="homelogo">
                <HomeContents /> 
        </div>
    </> 
    );
}

export default Home;

