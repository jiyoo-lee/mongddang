
const Profile = () => {
    return (
        <div className="profile_wrapper">
            <div className="img_wrapper">
                    <img className='profile_img' src='../.././img/present_logo.png'/>
            </div>
            <div className="social_wrapper">
                <div className="social"> 팔로우  <span className="people">12314141431</span> | 팔로잉 <span className="people"> 90897</span> </div>
            </div>
        <br/>
            <div className="top_menu_wrapper">
                <ul className ="top_menu_ul">
                    <li className="top_menu"> <img className='top_menu_logo' src='../.././img/home_logo.png'/> 홈 </li>
                    <li className="top_menu"> <img className='top_menu_logo' src='../.././img/drawer_logo.svg'/> 나의 서랍</li>
                    <li className="top_menu"> <img className='top_menu_logo' src='../.././img/guestbook_logo.svg'/>  방명록 </li>
                    <li className="top_menu"> <img className='top_menu_logo' src='../.././img/ac_logo.svg'/>  예술의 몽땅 </li>
                </ul>
            </div>
        </div>
    );
}

export default Profile;