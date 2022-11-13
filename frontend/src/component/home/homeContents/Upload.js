import MyButton from "../../button/MyButton";
import Profile from "./Profile";
import Friends from "./Friends";
import { useNavigate } from "react-router-dom";
import UploadedFriends from "./UploadedFriends";

const Upload = () => {

    const navigate = useNavigate();
    return (
        <div className="all_wrapper">
        <img className='login_logo' src={process.env.PUBLIC_URL + `../.././img/login_logo.png`} onClick={()=> {navigate('/home/feed')}} alt='login_logo'/>
        <div className="main_wrapper">
            <div className="menu_wrapper">
                <MyButton text={'피드'}  onClick={()=>{navigate('/home/feed')}}/>
                <MyButton text={'베스트'} type={'nothing'} onClick={()=>{navigate('/home/upload')}}/>
                <MyButton text={'지금 그림'}/>
                <MyButton text={'업로드'}/>
            </div>
            <div className="home_wrapper">
                <UploadedFriends/>
            </div>
            </div>
        <div className="sub_wrapper">
            <Profile/>
            <Friends/>
        </div>
    </div>
    );
}

export default Upload;