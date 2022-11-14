import { useNavigate } from "react-router-dom";
import MyButton from "../button/MyButton";

const MyDrawer = () => {

    const navigate = useNavigate();
    return (
        <div className="drawer_wrapper">
            <span className="userid_message">{sessionStorage.getItem("userId")}</span> <span className="drawer_msg">님의 드랍 개수 : </span>
            <span className="userid_message"> 24 </span>
            <span className="drawer_msg">최대 몽땅 개수 : </span> <span className="userid_message"> 234 </span>
            <MyButton text={'드랍 생성'} onClick={()=>{navigate('/home/upload')}}/>
            <MyButton type={'negative'} text={'삭제'}/>
        <br/>
        <br/>
        <br/>
        <br/>
            <div className="drops_list">
                <img className="drops"src= {process.env.PUBLIC_URL + `../.././img/test5.jpeg`}/>
                <span className="drawer_msg">나의 추억</span>
            </div>
            <div className="drops_list">
                <img className="drops"src= {process.env.PUBLIC_URL + `../.././img/test6.jpeg`}/>
                <span className="drawer_msg" >20세기 소녀</span>
            </div>
            <div className="drops_list">
                <img className="drops"src= {process.env.PUBLIC_URL + `../.././img/test2.jpg`}/>
                <span className="drawer_msg">편의점 알바로 살아남기</span>
            </div>
            <div className="drops_list">
                <img className="drops"src= {process.env.PUBLIC_URL + `../.././img/test9.jpeg`}/>
                <span className="drawer_msg">재밌다</span>
            </div>
            <br/>
        </div>
    );
}

export default MyDrawer;