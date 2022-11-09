import React from "react";
import './HomeContents.css';
import MyButton from "../button/MyButton"
import { useNavigate } from "react-router-dom";

function HomeContents () {

    const navigate = useNavigate();

    return (
            <div className="home">
                <img src='img/main.png' alt='main' className="homeimg"/>
                <span className="hometext"> 그림을 통해 자신의 마음을 나누어보세요 </span>
                <br/>
            <div>
                <MyButton text={'로그인'} onClick={()=> {navigate('/login')}}/>
                <MyButton text={'회원가입'} onClick={()=> {navigate('/join')}}/>
            </div>    
            </div>
    );
}

export default HomeContents;