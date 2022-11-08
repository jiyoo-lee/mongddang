import React from "react";
import './HomeContents.css';
import { useNavigate } from "react-router-dom";

function HomeContents () {

    const navigate = useNavigate();

    return (
            <div className="home">
                <img src='img/main.png' alt='main' className="homeimg"/>
                <span className="hometext"> 그림을 통해 자신의 마음을 나누어보세요 </span>
                <button onClick={()=> {
                    navigate('/login')
                }}>LOGIN</button>
                <button>SIGNUP</button>
            </div>
    );
}

export default HomeContents;